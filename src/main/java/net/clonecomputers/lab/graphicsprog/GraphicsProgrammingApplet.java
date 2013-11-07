package net.clonecomputers.lab.graphicsprog;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.*;

import net.clonecomputers.lab.util.*;

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
		/*String name = JOptionPane.showInputDialog(
				"Input class name for drawer type", "Drawer");
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
		}*/

		Set<Class<? extends AbstractDrawer>> drawersInPackage = ReflectionsHelper.findAllImplementations(AbstractDrawer.class);
		Set<AbstractDrawer> drawers = new HashSet<AbstractDrawer>();
		for(Class<? extends AbstractDrawer> a : drawersInPackage) {
			try {
				Constructor<? extends AbstractDrawer> constructor = a.getConstructor(DrawGrid.class);
				AbstractDrawer newDrawer = constructor.newInstance(d);
				drawers.add(newDrawer);
			} catch(NoSuchMethodException e) {
				System.err.println(a.getSimpleName() + " needs a proper constructor");
			} catch(Exception e) {
				System.err.println("Error initializing drawer class " + a.getSimpleName());
				e.printStackTrace();
			}
		}
		JList drawerList = new JList(drawers.toArray());
		drawerList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		drawerList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
				return super.getListCellRendererComponent(list, value.getClass().getSimpleName(), index, isSelected, cellHasFocus);
			}
		});
		JButton ok = new JButton("OK");
		JFrame drawerChooser = new JFrame("Choose an implementation");
		drawerChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DrawerChooserListener l = new DrawerChooserListener(drawerList, drawerChooser);
		drawerChooser.getRootPane().setDefaultButton(ok);
		ok.addActionListener(l);
		drawerList.addMouseListener(l);
		drawerChooser.addKeyListener(l);
		Container drawerChooserPane = drawerChooser.getContentPane();
		drawerChooserPane.setLayout(new BorderLayout());
		drawerChooserPane.add(new JScrollPane(drawerList),BorderLayout.CENTER);
		drawerChooserPane.add(ok,BorderLayout.PAGE_END);
		drawerChooser.setResizable(false);
		drawerChooser.pack();
		drawerChooser.setVisible(true);
		while(!l.done) Thread.yield(); 
		AbstractDrawer drawer = l.drawer;
		if(drawer == null) System.exit(0);
		final DrawGridController c = new DrawGridController(d, drawer);
		d.setPreferredSize(new Dimension(w+10,h+10));
		return new JPanel[]{d,c};
	}
	
	private static class DrawerChooserListener extends MouseAdapter implements ActionListener,KeyListener {
		private final JList drawerList;
		private final JFrame drawerWindow;
		public AbstractDrawer drawer = null;
		public boolean done = false;
		public DrawerChooserListener(JList drawerList, JFrame drawerWindow){
			this.drawerList = drawerList;
			this.drawerWindow = drawerWindow;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			drawer = (AbstractDrawer)drawerList.getSelectedValue();
			done = true;
			drawerWindow.dispose();
		}
		@Override
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() > 1){
				drawer = (AbstractDrawer)drawerList.getSelectedValue();
				done = true;
				drawerWindow.dispose();
			}
		}
		@Override
		public void keyPressed(KeyEvent arg0) {
			// don't care
		}
		@Override
		public void keyReleased(KeyEvent arg0) {
			// don't care
		}
		@Override
		public void keyTyped(KeyEvent e) {
			if(e.isActionKey()){
				drawer = (AbstractDrawer)drawerList.getSelectedValue();
				done = true;
				drawerWindow.dispose();
			}
		}
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
		if(panels == null) {
			JOptionPane.showMessageDialog(null, "Failed to load Drawer!", "Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(0); //should we maybe exit nonzero?
		}
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
