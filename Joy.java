
public class Joy {
	
	public int a;
	public int b;
	
	public Joy(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	public static int add(int a, int b) {
		return (a+b);
	}
	
	public static void main(String args[]) {
		
		Joy j = new Joy(4,5);
		System.out.print(add(10, 12));
		
	}

}
