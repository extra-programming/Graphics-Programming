package net.clonecomputers.lab.graphicsprog;

import javax.swing.*;

import org.mbertoli.jfep.*;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.List;

@SuppressWarnings("serial")
public class DrawGridController extends JPanel {
	public AbstractDrawer drawer;
	
	public DrawGridController(DrawGrid c, AbstractDrawer drawer){
		this.drawer = drawer;
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		final Method[] api = drawer.getClass().getMethods();
		Arrays.sort(api, new Comparator<Method>(){
			@Override
			public int compare(Method a, Method b) {
				return name(a).compareTo(name(b));
			}
		});
		List<JButton> buttons = new LinkedList<JButton>();
		for(int i = 0; i < api.length; i++){
			if(!AbstractDrawer.class.isAssignableFrom(api[i].getDeclaringClass())) continue;
			if(!Modifier.isPublic(api[i].getModifiers())) continue;
			JButton b = new JButton(name(api[i]));
			b.addActionListener(new DrawButtonListener(api[i]));
			buttons.add(b);
		}
		for(JButton b: buttons) this.add(b);
	}
	
	public class DrawButtonListener implements ActionListener{
		Method m;
		public DrawButtonListener(Method m){
			this.m = m;
		}
		@Override
		public void actionPerformed(ActionEvent e){
			/*Class<?>[] types = m.getParameterTypes();
			Object[] paramaters = new Object[types.length];
			Annotation[][] nameAnnotations = m.getParameterAnnotations();
			String[] names = new String[types.length];
			for(Annotation[] a: m.getParameterAnnotations()){
				
			}*/
			Object[] params = new Object[m.getParameterTypes().length];
			if(params.length > 0){
				String[] stringParams = 
					ask("input comma seperated paramaters for "+m.getName()+"\n"+params(m));
				if(stringParams == null) return;
				if(stringParams.length != params.length){
					JOptionPane.showMessageDialog(DrawGridController.this,
							"Wrong number of arguments",
							"Error", JOptionPane.OK_OPTION);
					return;
				}
			Class<?>[] paramTypes = m.getParameterTypes();
			try{
			for(int i = 0; i < params.length; i++){
				if(paramTypes[i].equals(int.class)){
					//params[i] = Integer.parseInt(stringParams[i]);
					params[i] = (int)new Parser("round("+stringParams[i]+")").getValue();
				}else if(paramTypes[i].equals(double.class)){
					params[i] = new Parser(stringParams[i]).getValue();
				}else if(paramTypes[i].equals(Color.class)){
					params[i] = getColor(stringParams[i]);
				}else if(paramTypes[i].equals(boolean.class)){
					params[i] = getBool(stringParams[i]);
				}else{
					params[i] = null;
				}
			}
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(DrawGridController.this,
						"Wrong type of argument",
						"Error", JOptionPane.OK_OPTION);
			}catch(ParseError e1){
				JOptionPane.showMessageDialog(DrawGridController.this,
						"Error parsing argument",
						"Error", JOptionPane.OK_OPTION);
			}
			}else{
				params = new Object[]{};
			}
			Object ret = null;
			try {
				ret = m.invoke(drawer, params);
			} catch (IllegalArgumentException e1) {
				JOptionPane.showMessageDialog(DrawGridController.this,
						"Wrong number of arguments",
						"Error", JOptionPane.OK_OPTION);
			} catch (IllegalAccessException e1) {
				throw new RuntimeException(e1);
			} catch (InvocationTargetException e1) {
				throw new RuntimeException(e1);
			}
			if(ret != null){
				JOptionPane.showMessageDialog(DrawGridController.this,
						ret.toString(),
						"Output", JOptionPane.OK_OPTION);
			}
		}
	}

	public String[] ask(String title) {
		String s = JOptionPane.showInputDialog(this, title);
		if(s == null) return null;
		return s.split(",");
	}

	public String object(Method m){
		return m.getDeclaringClass().getSimpleName();
	}

	public String params(Method m){
		String s = "";
		for(Class<?> c: m.getParameterTypes()){
			s += c.getSimpleName();
			s += ", ";
		}
		if(s.length() > 0) s = s.substring(0, s.length()-2);
		return s;
	}
	
	public String shortName(Method m){
		return m.getName();
	}
	
	public String name(Method m){
		return shortName(m)+"("+params(m)+")";
	}
	
	public String fullName(Method m){
		return object(m)+"."+name(m);
	}

	public Object getColor(String s) {
		if(s.equalsIgnoreCase("BLACK") || s.equalsIgnoreCase("k")) return Color.BLACK;
		if(s.equalsIgnoreCase("BLUE") || s.equalsIgnoreCase("b")) return Color.BLUE;
		if(s.equalsIgnoreCase("CYAN") || s.equalsIgnoreCase("c")) return Color.CYAN;
		if(s.equalsIgnoreCase("DARK_GRAY") || s.equalsIgnoreCase("dark")) return Color.DARK_GRAY;
		if(s.equalsIgnoreCase("GRAY") || s.equalsIgnoreCase("grey")) return Color.GRAY;
		if(s.equalsIgnoreCase("GREEN") || s.equalsIgnoreCase("g")) return Color.GREEN;
		if(s.equalsIgnoreCase("LIGHT_GRAY") || s.equalsIgnoreCase("light")) return Color.LIGHT_GRAY;
		if(s.equalsIgnoreCase("MAGENTA") || s.equalsIgnoreCase("m")) return Color.MAGENTA;
		if(s.equalsIgnoreCase("ORANGE") || s.equalsIgnoreCase("o")) return Color.ORANGE;
		if(s.equalsIgnoreCase("PINK") || s.equalsIgnoreCase("p")) return Color.PINK;
		if(s.equalsIgnoreCase("RED") || s.equalsIgnoreCase("r")) return Color.RED;
		if(s.equalsIgnoreCase("WHITE") || s.equalsIgnoreCase("w")) return Color.WHITE;
		if(s.equalsIgnoreCase("YELLOW") || s.equalsIgnoreCase("y")) return Color.YELLOW;
		String[] sa = s.split(":");
		if(sa.length == 3) {
			return new Color(
				Integer.parseInt(sa[0]),
				Integer.parseInt(sa[1]),
				Integer.parseInt(sa[2])
			);
		}
		return Color.BLUE;
	}
	
	public boolean getBool(String s) {
		return (
			s.equalsIgnoreCase("t") ||
			s.equalsIgnoreCase("true") ||
			s.equalsIgnoreCase("y") ||
			s.equalsIgnoreCase("yes")
		); // if not true, then false
	}
}
