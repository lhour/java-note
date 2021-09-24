package test;

public class Test2 {
    public static void main(String[] args) {
		
	}
}


class FatherClass2 {
	int n;

	FatherClass2() {
		System.out.println("execute -> FatherClass2()");
	}

	FatherClass2(int n) {
		System.out.println("execute -> FatherClass2(int n)");
		this.n = n;
	}

	void setN(int n) {
		System.out.println("execute -> FatherClass2->setN");
		this.n = n;
	}

	int getN() {
		return this.n;
	}
}

// SubClass 类继承
class SonClass2 extends FatherClass2 {
	int n;

	SonClass2() { // 自动调用父类的无参数构造器
		System.out.println("execute -> SonClass2");
	}

	public SonClass2(int n) {
		super(300); // 调用父类中带有参数的构造器
		System.out.println("execute -> SonClass2(int n):" + n);
		this.n = n;
	}

	@Override
	public void setN(int n) {
		super.setN(n);
		System.out.println("execute ->  SonClass2 -> setN");
		this.n = n + 100;
	}

	@Override
	public int getN() {
		System.out.println("execute -> SonClass -> getN");
		return this.n;
	}
}