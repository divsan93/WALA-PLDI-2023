public class Test {
    public Test(int i) {
        //super(i);
    }

    void switchTest()
    {
        int day = 4;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
        }
    }
    void LoopTest()
    {
        for(int i =0; i<10; i++)
        {

        }
    }
    void IFStmtTest()
    {
        if(0==0)
        {

        }
    }
    void BreakTest()
    {
        for(int i = 0; i<10 ; i++)
        {
            break;
        }
    }
    void ContinueTest()
    {
        for(int i = 0; i<10 ; i++)
        {
            continue;
        }
    }

    void ForInLoop()
    {
        int[] marks = { 125, 132, 95, 116, 110 };
        for (int num:marks)
        {
            System.out.println(num);
        }
    }

    void BlockStatement()
    {
         int value;
    }
    void TryStatement()
    {
        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }
    void CatchStatement()
    {
        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        }
    }

    void DECL_STMT()
    {
        int j = 0;
    }

    Object Return_STMT()
    {
        return null;
    }
    void IfTest()
    {
        if(0==0)
        {
            System.out.println("0");
        }
    }
    void throwTest()
    {
        try
        {
            throw new NullPointerException("demo");
        }
        catch(NullPointerException e)
        {
            System.out.println("Caught.");
            throw e;
        }
    }
    void AssignTest()
    {
        int j;
        j =0;
    }
    void Assign_PreTest()
    {
        int j=10;
        j =--j;
    }
    void Assign_PostTest()
    {
        int j=10;
        j =j++;
    }
    void EmptyTest()
    {
        int j,x;
        j =0;
        x=0;
        j=x;//Empty
    }
    void CatchTest()
    {
        try
        {
            throw new NullPointerException("demo");
        }
        catch(NullPointerException e)//Catch
        {
            System.out.println("Caught.");
            throw e;
        }
    }
    void CallTest()
    {
        CatchTest();
    }
    void BlockExprTest()
    {
        int x =0;
    }
    void BinaryExprTest()
    {
        String hello = (String)("he" + "ll")+"o";
    }
    void UnaryTest()
    {
        int n1 = 20;
        n1 = -n1;
    }
    void IfExprTest()
    {
        if(0==0)
        {
            System.out.println("True");
        }
        else
        {
            System.out.println("False");
        }
    }

    void AndOrExpr()
    {
        int x = 5;
        System.out.println(x > 3 && x < 10);
    }

    void NewTest()
    {
        String a  = new String("a");
    }

    void SuperTest()
    {
        new Test(10);
    }
    //Test THIS node type.
    Test()
    {
        this(0);
    }
    void CastTest()
    {
        double myDouble = 9.78d;
        int myInt = (int) myDouble;
    }

    void InstanceOfTest()
    {
        System.out.println("hello" instanceof String);
    }
    void ArrayRefTest()
    {
        int[] myNumbers = {1, 2, 3};
        System.out.println(myNumbers[0]);
    }
    void ArrayLengthTest()
    {
        int[] myNumbers = {1, 2, 3};
        System.out.println(myNumbers.length);
    }
    void ArrayLiteralTest()
    {
        int[] myNumbers;
    }

    void TypeOfTest()
    {
        int[] myNumbers;
    }

    void LocalScopeTest()
    {
        String hello = (String)("he" + "ll")+"o";
    }

    void ConstantTest()
    {
        double f = 3.14;
    }
    void AssertTrue()
    {
        assert null != null && 1> 0 : "Failed precondition";
    }

    void NamedEntityTest()
    {
        System.out.println("Hello");
    }
    public static void main(String[] args) {
        int sum= 0;
        for(int i=0; i < 10; i++) {
            sum += i;
        }
        }
}
