package com.jianwen.segment.designpatterns.build.difficultexample;

/**
 * 在本例中将具体建造者合并到了产品对象中，并将产品对象的构造函数私有化，防止客户端不使用构建器来构建产品对象，而是直接去使用new来构建产品对象所导致的问题。另外，这个构建器的功能就是为了创建被构建的对象，完全可以不用单独一个类。
 *
 * @Author: jianwen
 * @since: 2018/12/17 下午12:14
 */
public class Client {
    /**
     * 考虑这样一个实际应用，要创建一个保险合同的对象，里面很多属性的值都有约束，要求创建出来的对象是满足这些约束规则的。约束规则比如：保险合同通常情况下可以和个人签订，也可以和某个公司签订，但是一份保险合同不能同时与个人和公司签订。这个对象里有很多类似这样的约束，采用建造模式来构建复杂的对象，通常会对建造模式进行一定的简化，因为目标明确，就是创建某个复杂对象，因此做适当简化会使程序更简洁。大致简化如下：
     * 　　●　　由于是用Builder模式来创建某个对象，因此就没有必要再定义一个Builder接口，直接提供一个具体的建造者类就可以了。
     * 　　●　　对于创建一个复杂的对象，可能会有很多种不同的选择和步骤，干脆去掉“导演者”，把导演者的功能和Client的功能合并起来，也就是说,Client这个时候就相当于导演者，它来指导构建器类去构建需要的复杂对象。
     */
    public static void main(String[] args) {
        //创建构建器对象
        InsuranceContract.ConcreteBuilder builder =
                new InsuranceContract.ConcreteBuilder("9527", 123L, 456L);
        //设置需要的数据，然后构建保险合同对象
        InsuranceContract contract =
                builder.setPersonName("小明").setOtherData("test").build();
        //操作保险合同对象的方法
        contract.someOperation();
    }
}
