package pages;
import core.Driver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class BasicScreen extends Driver {

    PageObjects chatPage;
    public double resultDouble = 0;
    public int resultInt = 0;
    public String operand1 = "";
    public String operand2 = "";
    public String operator;
    public String digits = "0123456789";
    public String operators = "*/-+%";
    public boolean isPressedOp = false;
    public boolean isPressedPoint = false;

    public BasicScreen() throws Exception {
        chatPage = new PageObjects();
        PageFactory.initElements(new AppiumFieldDecorator(this.getDriver()),chatPage);
    }

    public void clickNumber(AndroidElement number) throws Exception {
        if (digits.contains(number.getText())){
            number.click();
            System.out.println(number.getText() + " button clicked.");
            Log(number.getText() + " button clicked.\n");
            if (!isPressedOp){
                operand1 += number.getText();
            }
            else {
                operand2 += number.getText();
            }
        }
        else{
            Log("This element is not a number!\n");
            throw new Exception("This element is not a number!");
        }
    }

    public void clickOperator(AndroidElement operatorElement) throws IOException {
        if (operators.contains(operatorElement.getText())){
            operatorElement.click();
            operator = operatorElement.getAttribute("content-desc");
            System.out.println(operator + " button clicked.");
            Log(operator + " button clicked.\n");
            isPressedOp = true;
        }
        else {
            isPressedOp = false;
        }
    }

    public void clickPoint() throws IOException {
        chatPage.dotBtn.click();
        isPressedPoint = true;
        System.out.println(". button clicked.");
        Log(". button clicked.\n");
        if (isPressedOp){
            operand2 += '.';
        }
        else{
            operand1 += '.';
        }
    }

    public void clickClear() throws IOException {
        chatPage.clearBtn.click();
        System.out.println("clear button clicked.");
        Log("Clear button clicked.\n");
    }

    public void calculateResult() throws Exception {
        chatPage.equalBtn.click();
       if(operator.equals("artı")){
           if (isPressedPoint){
               resultDouble = Double.parseDouble(operand1) + Double.parseDouble(operand2);
               System.out.println("Computer result is "+ resultDouble);
               Log("Computer result is "+ resultDouble +"\n");
           }
           else{
               resultInt = Integer.parseInt(operand1) + Integer.parseInt(operand2);
               System.out.println("Computer result is "+ resultInt);
               Log("Computer result is "+ resultInt +"\n");
           }

       }
       else if(operator.equals("eksi")) {
           if (isPressedPoint){
               resultDouble = Double.parseDouble(operand1) - Double.parseDouble(operand2);
               System.out.println("Computer result is "+ resultDouble);
               Log("Computer result is "+ resultDouble +"\n");
           }
           else{
               resultInt = Integer.parseInt(operand1) - Integer.parseInt(operand2);
               System.out.println("Computer result is "+ resultInt);
               Log("Computer result is "+ resultInt +"\n");
           }

       }
       else if(operator.equals("bölü")) {
           if (isPressedPoint){
               if (Double.parseDouble(operand1) % Double.parseDouble(operand2) == 0)
               {
                   resultInt =(int) (Double.parseDouble(operand1) / Double.parseDouble(operand2));
                   System.out.println("Computer result is "+ resultInt);
                   Log("Computer result is "+ resultInt +"\n");
               }
               else{
                   resultDouble = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                   System.out.println("Computer result is "+ resultDouble);
                   Log("Computer result is "+ resultDouble +"\n");
               }

           }
           else{
               if (Integer.parseInt(operand1) % Integer.parseInt(operand2) == 0){
                   resultInt = Integer.parseInt(operand1) / Integer.parseInt(operand2);
                   System.out.println("Computer result is "+ resultInt);
                   Log("Computer result is "+ resultInt +"\n");
               }
               else{
                   resultDouble = Integer.parseInt(operand1) / Integer.parseInt(operand2);
                   System.out.println("Computer result is "+ resultDouble);
                   Log("Computer result is "+ resultDouble +"\n");
               }

           }

       }
       else if(operator.equals("×")) {
           if (isPressedPoint){
               resultDouble = Double.parseDouble(operand1) * Double.parseDouble(operand2);
               System.out.println("Computer result is "+ resultDouble);
               Log("Computer result is "+ resultDouble +"\n");
           }
           else{
               resultInt = Integer.parseInt(operand1) * Integer.parseInt(operand2);
               System.out.println("Computer result is "+ resultInt);
               Log("Computer result is "+ resultInt +"\n");
           }

       }
       else if(operator.equals("yüzde")) {
           if (isPressedPoint){
               resultDouble = (Double.parseDouble(operand1) * Double.parseDouble(operand2))/100;
               System.out.println("Computer result is "+ resultDouble);
               Log("Computer result is "+ resultDouble +"\n");
           }
           else{
               resultInt = (Integer.parseInt(operand1) * Integer.parseInt(operand2))/100;
               System.out.println("Computer result is "+ resultInt);
               Log("Computer result is "+ resultInt +"\n");
           }

       }
       else{
           throw new Exception("This Element is not an operator!");
       }
        System.out.println("Phone's calculator result is "+ chatPage.resultText.getText());
        Log("Phone's calculator result is "+ chatPage.resultText.getText() +"\n");
    }

    public void checkResult() throws Exception {
        if (resultInt == 0){
            if (!chatPage.resultText.getText().equals(Double.toString(resultDouble))){
                Log("The result is wrong! Test Failed!\n\n");
                throw new Exception("The result is wrong! Test Failed!\n");
            }
            else{
                System.out.println("Results Matched! Test Passed!\n");
                Log("Results Matched! Test Passed!\n\n");
            }
        }
        else{
            if (!chatPage.resultText.getText().equals(Integer.toString(resultInt))){
                Log("The result is wrong! Test Failed!\n\n");
                throw new Exception("The result is wrong! Test Failed!\n");
            }
            else{
                System.out.println("Results Matched! Test Passed!\n");
                Log("Results Matched! Test Passed!\n\n");
            }
        }

    }

    public void multiplyOperation() throws Exception {
        Log("Multiply Test Started\n");
        clickNumber(chatPage.fiveBtn);
        clickNumber(chatPage.zeroBtn);
        clickOperator(chatPage.multiplyBtn);
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.zeroBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void multiplyTestWithPoint() throws Exception {
        Log("Multiply Test Point Test Started\n");
        clickNumber(chatPage.sixBtn);
        clickPoint();
        clickNumber(chatPage.oneBtn);
        clickOperator(chatPage.multiplyBtn);
        clickNumber(chatPage.twoBtn);
        clickPoint();
        clickNumber(chatPage.fiveBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void addOperation() throws Exception {
        Log("Add Test Started\n");
        clickNumber(chatPage.eightBtn);
        clickNumber(chatPage.threeBtn);
        clickOperator(chatPage.addBtn);
        clickNumber(chatPage.sevenBtn);
        clickNumber(chatPage.twoBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void addTestWithPoint() throws Exception {
        Log("Add With Point Test Started\n");
        clickNumber(chatPage.nineBtn);
        clickPoint();
        clickNumber(chatPage.fiveBtn);
        clickOperator(chatPage.addBtn);
        clickNumber(chatPage.eightBtn);
        clickPoint();
        clickNumber(chatPage.oneBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void subOperation() throws Exception {
        Log("Sub Test Started\n");
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.eightBtn);
        clickNumber(chatPage.threeBtn);
        clickOperator(chatPage.subBtn);
        clickNumber(chatPage.fiveBtn);
        clickNumber(chatPage.oneBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void subTestWithPoint() throws Exception {
        Log("Sub With Point Test Started\n");
        clickNumber(chatPage.sixBtn);
        clickNumber(chatPage.sixBtn);
        clickPoint();
        clickNumber(chatPage.fiveBtn);
        clickOperator(chatPage.addBtn);
        clickNumber(chatPage.threeBtn);
        clickPoint();
        clickNumber(chatPage.twoBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void divOperation() throws Exception {
        Log("Div Test Started\n");
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.eightBtn);
        clickOperator(chatPage.divBtn);
        clickNumber(chatPage.threeBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void divTestWithPoint() throws Exception {
        Log("Div With Point Test Started\n");
        clickNumber(chatPage.twoBtn);
        clickNumber(chatPage.sixBtn);
        clickPoint();
        clickNumber(chatPage.fiveBtn);
        clickOperator(chatPage.divBtn);
        clickNumber(chatPage.fiveBtn);
        clickPoint();
        clickNumber(chatPage.twoBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void percentOperation() throws Exception {
        Log("Percent Test Started\n");
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.zeroBtn);
        clickNumber(chatPage.zeroBtn);
        clickOperator(chatPage.percentBtn);
        clickNumber(chatPage.threeBtn);
        clickNumber(chatPage.fiveBtn);
        calculateResult();
        checkResult();
        clickClear();
    }

    public void percentTestWithPoint() throws Exception {
        Log("Percent With Point Test Started\n");
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.zeroBtn);
        clickNumber(chatPage.zeroBtn);
        clickOperator(chatPage.percentBtn);
        clickNumber(chatPage.fiveBtn);
        clickNumber(chatPage.oneBtn);
        clickPoint();
        clickNumber(chatPage.twoBtn);
        calculateResult();
        checkResult();
        clickClear();
    }



    class PageObjects{

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_0")
        public AndroidElement zeroBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_1")
        public AndroidElement oneBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_2")
        public AndroidElement twoBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_3")
        public AndroidElement threeBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_4")
        public AndroidElement fourBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_5")
        public AndroidElement fiveBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_6")
        public AndroidElement sixBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_7")
        public AndroidElement sevenBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_8")
        public AndroidElement eightBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/digit_9")
        public AndroidElement nineBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/op_add")
        public AndroidElement addBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/op_sub")
        public AndroidElement subBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/op_mul")
        public AndroidElement multiplyBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/op_div")
        public AndroidElement divBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/op_pct")
        public AndroidElement percentBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/dec_point")
        public AndroidElement dotBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/lparen")
        public AndroidElement parenBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/del")
        public AndroidElement delBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/clr")
        public AndroidElement clearBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/eq")
        public AndroidElement equalBtn;

        @AndroidFindBy(id = "com.oneplus.calculator:id/result")
        public AndroidElement resultText;

        @AndroidFindBy(id = "com.oneplus.calculator:id/formula")
        public AndroidElement formulText;
    }
}
