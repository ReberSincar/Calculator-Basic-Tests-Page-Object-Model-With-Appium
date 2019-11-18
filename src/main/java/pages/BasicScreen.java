package pages;
import core.Driver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

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

            if (!isPressedOp){
                operand1 += number.getText();
            }
            else {
                operand2 += number.getText();
            }
        }
        else{
            throw new Exception("This element is not a number!");
        }
    }

    public void clickOperator(AndroidElement operatorElement){
        if (operators.contains(operatorElement.getText())){
            operatorElement.click();
            operator = operatorElement.getAttribute("content-desc");
            isPressedOp = true;
        }
        else {
            isPressedOp = false;
        }
    }

    public void clickPoint(){
        chatPage.dotBtn.click();
        isPressedPoint = true;
        if (isPressedOp){
            operand2 += '.';
        }
        else{
            operand1 += '.';
        }
    }

    public void calculateResult() throws Exception {
        chatPage.equalBtn.click();
       if(operator.equals("artı")){
           if (isPressedPoint){
               resultDouble = Double.parseDouble(operand1) + Double.parseDouble(operand2);
           }
           else{
               resultInt = Integer.parseInt(operand1) + Integer.parseInt(operand2);
           }

       }
       else if(operator.equals("eksi")) {
           if (isPressedPoint){
               resultDouble = Double.parseDouble(operand1) - Double.parseDouble(operand2);
           }
           else{
               resultInt = Integer.parseInt(operand1) - Integer.parseInt(operand2);
           }

       }
       else if(operator.equals("bölü")) {
           if (isPressedPoint){
               if (Double.parseDouble(operand1) % Double.parseDouble(operand2) == 0)
               {
                   resultInt =(int) (Double.parseDouble(operand1) / Double.parseDouble(operand2));
               }
               else{
                   resultDouble = Double.parseDouble(operand1) / Double.parseDouble(operand2);
               }

           }
           else{
               if (Integer.parseInt(operand1) % Integer.parseInt(operand2) == 0){
                   resultInt = Integer.parseInt(operand1) / Integer.parseInt(operand2);
               }
               else{
                   resultDouble = Integer.parseInt(operand1) / Integer.parseInt(operand2);
               }

           }

       }
       else if(operator.equals("×")) {
           if (isPressedPoint){
               resultDouble = Double.parseDouble(operand1) * Double.parseDouble(operand2);
           }
           else{
               resultInt = Integer.parseInt(operand1) * Integer.parseInt(operand2);
           }

       }
       else if(operator.equals("yüzde")) {
           if (isPressedPoint){
               resultDouble = (Double.parseDouble(operand1) * Double.parseDouble(operand2))/100;
           }
           else{
               resultInt = (Integer.parseInt(operand1) * Integer.parseInt(operand2))/100;
           }

       }
       else{
           throw new Exception("This Element is not an operator!");
       }
    }

    public void checkResult() throws Exception {
        if (resultInt == 0){
            if (!chatPage.resultText.getText().equals(Double.toString(resultDouble))){
                throw new Exception("The result is wrong!");
            }
        }
        else{
            if (!chatPage.resultText.getText().equals(Integer.toString(resultInt))){
                throw new Exception("The result is wrong!");
            }
        }

    }

    public void multiplyOperation() throws Exception {
        clickNumber(chatPage.fiveBtn);
        clickNumber(chatPage.zeroBtn);
        clickOperator(chatPage.multiplyBtn);
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.zeroBtn);
        calculateResult();
        checkResult();
    }

    public void multiplyTestWithPoint() throws Exception {
        clickNumber(chatPage.sixBtn);
        clickPoint();
        clickNumber(chatPage.oneBtn);
        clickOperator(chatPage.multiplyBtn);
        clickNumber(chatPage.twoBtn);
        clickPoint();
        clickNumber(chatPage.fiveBtn);
        calculateResult();
        checkResult();
    }

    public void addOperation() throws Exception {
        clickNumber(chatPage.eightBtn);
        clickNumber(chatPage.threeBtn);
        clickOperator(chatPage.addBtn);
        clickNumber(chatPage.sevenBtn);
        clickNumber(chatPage.twoBtn);
        calculateResult();
        checkResult();
    }

    public void addTestWithPoint() throws Exception {
        clickNumber(chatPage.nineBtn);
        clickPoint();
        clickNumber(chatPage.fiveBtn);
        clickOperator(chatPage.addBtn);
        clickNumber(chatPage.eightBtn);
        clickPoint();
        clickNumber(chatPage.oneBtn);
        calculateResult();
        checkResult();
    }

    public void subOperation() throws Exception {
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.eightBtn);
        clickNumber(chatPage.threeBtn);
        clickOperator(chatPage.subBtn);
        clickNumber(chatPage.fiveBtn);
        clickNumber(chatPage.oneBtn);
        calculateResult();
        checkResult();
    }

    public void subTestWithPoint() throws Exception {
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
    }

    public void divOperation() throws Exception {
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.eightBtn);
        clickOperator(chatPage.divBtn);
        clickNumber(chatPage.threeBtn);
        calculateResult();
        checkResult();
    }

    public void divTestWithPoint() throws Exception {
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
    }

    public void percentOperation() throws Exception {
        clickNumber(chatPage.oneBtn);
        clickNumber(chatPage.zeroBtn);
        clickNumber(chatPage.zeroBtn);
        clickOperator(chatPage.percentBtn);
        clickNumber(chatPage.threeBtn);
        clickNumber(chatPage.fiveBtn);
        calculateResult();
        checkResult();
    }

    public void percentTestWithPoint() throws Exception {
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
