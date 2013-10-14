package net.clonecomputers.lab.graphicsprog;

import java.awt.*;
import java.lang.reflect.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GraphicsProgrammingApplet extends JApplet {
	
	@Override public void init(){
		this.setSize(1225, 720);
		JPanel[] panels = getPanels(700,700);
		this.setLayout(new FlowLayout());
		this.add(panels[0]);
		this.add(panels[1]);
	}
	
	public static JPanel[] getPanels(int w, int h){
		final DrawGrid d = new DrawGrid(w,h);
		String name = JOptionPane.showInputDialog(
				"Input class name for drawer type", "SampleDrawer");
		Class<?> cl;
		try {
			cl = Class.forName("net.clonecomputers.lab.graphicsprog."+name);
		} catch (ClassNotFoundException e) {
			System.err.println("Not a valid class");
			return null;
		}
		Object o;
		try {
			o = cl.getConstructor(DrawGrid.class).newInstance(d);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		} catch (SecurityException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
		AbstractDrawer drawer = null;
		if(o instanceof AbstractDrawer){
			drawer = (AbstractDrawer) o;
		}else{
			System.err.println("Not a drawer");
			return null;
		}
		final DrawGridController c = new DrawGridController(d, drawer);
		d.setPreferredSize(new Dimension(w+10,h+10));
		return new JPanel[]{d,c};
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final int w, h;
		if(args.length == 2){
			w = Integer.parseInt(args[0]);
			h = Integer.parseInt(args[1]);
		}else{
			w = 700;
			h = 700;
		}
		final JPanel[] panels = getPanels(w,h);
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run() {
				JFrame drawing = new JFrame("Drawing");
				drawing.add(panels[0]);
				drawing.pack();
				drawing.setResizable(false);
				drawing.setVisible(true);
				drawing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				JFrame controller = new JFrame("DrawGridController");
				controller.add(panels[1]);
				controller.pack();
				controller.setResizable(false);
				controller.setLocation(
						drawing.getLocation().x+drawing.getWidth()+5,
						drawing.getLocation().y);
				controller.setVisible(true);
				controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
}
