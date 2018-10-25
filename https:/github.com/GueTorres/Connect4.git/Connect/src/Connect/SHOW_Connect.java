package Connect;

public class SHOW_Connect {


		public static void main(String[] args) {
			javax.swing.SwingUtilities.invokeLater( new Runnable(){
				@Override
				public void run() {
					Connect_GUI gui = new Connect_GUI();//here is the magic
				}
			});
		}

	}


