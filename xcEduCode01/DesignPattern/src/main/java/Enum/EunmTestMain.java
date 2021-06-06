package Enum;

public class EunmTestMain {
    /**
     * compareTo 比较枚举与指定对象的顺序
     * equals 比较枚举常量
     * getDeclaringClass 返回与此枚举常量类型对应的class对象
     * name 返回枚举的常量名称
     * ordinal 返回枚举常量的序数，初始化为零
     * toString 返回枚举常量的名称
     * valueOf(class<T> enumType,String name) 返回指定枚举名称的常量
     *注意枚举是可以为用==去做判断的
     * @param args
     */
    public static void main(String[] args) {
        boolean equals = EnumTest.ONE==EnumTest.ONE;
//        System.out.println(equals);
//        Class<EnumTest> declaringClass = EnumTest.ONE.getDeclaringClass();
//        System.out.println(declaringClass);
//        String name = EnumTest.ONE.name();
//        int ordinal = EnumTest.ONE.ordinal();
//        String name1 = EnumTest.ONE.toString();
//        EnumTest enumTest = EnumTest.valueOf("ONE");


        System.out.println(equals);
//        System.out.println(name1);
//        EnumTest[] enumTest={EnumTest.ONE,EnumTest.TWO,EnumTest.THREE};
//        for (int i = 0; i < enumTest.length; i++) {
//            EnumTest test = enumTest[i];
//            test.equals()
//        }
    }
}
