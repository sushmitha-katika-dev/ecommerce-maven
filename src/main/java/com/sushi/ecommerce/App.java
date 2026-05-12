package com.sushi.ecommerce;

import com.sushi.ecommerce.model.Customer;
import com.sushi.ecommerce.ui.CustomerUI;
import com.sushi.ecommerce.ui.DashboardUI;
import com.sushi.ecommerce.ui.ProductUI;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        new DashboardUI().show();
    }
}
