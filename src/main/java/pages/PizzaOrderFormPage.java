package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.sample.test.demo.TestBase;

public class PizzaOrderFormPage extends TestBase {
	private static final String _PIZZA_SIZE = "pizza1Pizza";
	private static final String _TOPPINGS = "toppings1";
	private static final String _QTY = "pizza1Qty";
	private static final String _PLACE_ORDER = "placeOrder";
	private static final String _ORDR_SUBMISSION_DIALOG = "dialog";
	private static final String _USER_PHONE_NUM = "phone";
	private static final String _USER_NAME = "name";
	private static final String _CASH_PAYMENT = "cashpayment";
	private static final String _CARD_PAYMENT = "ccpayment";
	private static final String _RESET_ORDER ="reset";
	private static final String _PIZZA_COST ="pizza1Cost";


	public void selectPizzaSize(String pizza) {
		findElement(By.id(_PIZZA_SIZE)).click();
		Select pizzaType = new Select(findElement(By.id(_PIZZA_SIZE)));
		pizzaType.selectByValue(pizza);
	}

	public void selectPizzaTopping(String topping) {
		driver.findElement(By.className(_TOPPINGS)).click();
		Select pizzaTopping = new Select(findElement(By.className(_TOPPINGS)));
		pizzaTopping.selectByValue(topping);
	}

	public void selectQty(String qty) {
		findElement(By.id(_QTY)).clear();
		findElement(By.id(_QTY)).sendKeys(qty);
	}

	public void selectPickupInformation(String name, String phoneNum) {
		findElement(By.id(_USER_NAME)).sendKeys(name);
		findElement(By.id(_USER_PHONE_NUM)).sendKeys(phoneNum);
	}

	/**
	 * Method to select payment type
	 * 
	 * @param paymentType
	 */
	public void selectPaymentType(String paymentType) {
		if (paymentType.contains(paymentType))
			findElement(By.id(_CARD_PAYMENT));
		else
			findElement(By.id(_CASH_PAYMENT));
	}

	public void submitOrder() {
		findElement(By.id(_PLACE_ORDER)).click();
	}

	/**
	 * Verify Order submission form
	 * 
	 * @param price
	 */
	public void verifyOrderSubmission(double price) {
		findElement(By.id(_ORDR_SUBMISSION_DIALOG));
		String window = driver.getWindowHandle();
		driver.switchTo().window(window);
		Assert.assertTrue(findElement(By.id(_ORDR_SUBMISSION_DIALOG)).getText().contains(String.valueOf(price)),
				"Err: Order submission unsuccessful!");
	}

	private WebElement findElement(By id) {
		return driver.findElement(id);
	}
	
	public void verifySelectedPizzaPrice(double price){
		 Assert.assertEquals(findElement(By.id(_PIZZA_COST)).getText(), price, "Err: Incorrect Price");
	}
	
	public void verifyPizzaOrderPage() {
		 Assert.assertTrue(driver.getCurrentUrl().contains("index"), "Err: Page didn't load properly");
	}
	
	private void resetValueOnOrderPage() {
		findElement(By.id(_RESET_ORDER));
	}
	
	/**
	 * Verify values are reset on order form after clicking on reset button
	 */
	public void resetAndVerifyValues() {
		resetValueOnOrderPage();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(getSelectedValue(findElement(By.id(_PIZZA_SIZE))).isBlank(), "Err: Pizza size not reset");
		softAssert.assertTrue(getSelectedValue(findElement(By.className(_TOPPINGS))).isBlank(), "Err: Pizza topping1 not reset");
		softAssert.assertTrue(findElement(By.id(_QTY)).getText().isEmpty(), "Err: Quantity not reset");
		softAssert.assertTrue(findElement(By.id(_PIZZA_COST)).getText().isEmpty(), "Err: Pizza Cost not reset");
		softAssert.assertTrue(findElement(By.id(_USER_NAME)).getText().isEmpty(), "Err: Username not reset");
		softAssert.assertTrue(findElement(By.id(_USER_PHONE_NUM)).getText().isEmpty(), "Err: Phone number not reset");
		softAssert.assertAll();
	}
	
	private String getSelectedValue(WebElement name) {
		Select select = new Select(name);
		return select.getFirstSelectedOption().getText();
	}
}
