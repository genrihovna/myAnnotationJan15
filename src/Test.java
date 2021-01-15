public class Test {
    @AutoInitialize
    private Test2 test2;
    @AutoInitialize
    private Test3 test3;
    private Integer num = 40;

    public int foo(){
        return 10 + test2.test() + test3.test() + num;
    }
}
