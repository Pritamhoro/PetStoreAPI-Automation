package PetStore;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class orders 
{
	// Test to get the inventory from the PetStore API
	@Test(priority = 0)
	public void GetInventory()
	{
		given().
		when()
			.get("https://petstore.swagger.io/v2/store/inventory")
		.then()
			.log()
			.body().and().statusCode(200);
		
	}
	
	// Test to place an order in the PetStore
	@Test(priority = 1)
	public void PlaceOrder() 
	{
		 HashMap<String, Object> data = new HashMap<>();
		data.put("id", 1);
		data.put("petId", 0);
		data.put("quantity", 0);
		data.put("shipDate", "2025-01-26T14:29:54.132Z");
		data.put("status", "placed");
		data.put("complete", true);
		
		Response respone=
		
		given().
		contentType("application/json")
		.body(data).
		when()
			.post("https://petstore.swagger.io/v2/store/order").
		then()
		.log()
		.body()
		.body("status", equalTo("placed"))
		.and().statusCode(200)
		.and().extract().response();
	}
	
	// Test to retrieve an order by its ID
	@Test(priority = 3)
	public void GetOrderID()
	{
		given().
		when()
			.get("https://petstore.swagger.io/v2/store/order/1")
		.then()
			.log()
			.body().and().statusCode(200);
	}
	
	 // Test to check the latest inventory
	@Test(priority = 4)
	public void LatestGetInventory() throws InterruptedException
	{
		given().
		when()
			.get("https://petstore.swagger.io/v2/store/inventory")
		.then()
			.log()
			.body().and().statusCode(200);
		
		Thread.sleep(1500);
		
	}
	
	// Test to delete an order 
	@Test(priority = 5)
	public void DeletePurchaseId()
	{
		given().
		when()
		.delete("https://petstore.swagger.io/v2/store/order/1")
		.then()
		.statusCode(404)
		.and()
		.log().
		all();
	}
	
	// Test to ensure that the order is indeed deleted
	@Test(priority=6)
	public void GetDeleteedorderId()
	{
		given().
		when()
			.get("https://petstore.swagger.io/v2/store/order/1")
		.then()
			.log()
			.body().and().statusCode(404);
		
	}
	
	
}


