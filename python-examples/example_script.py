#!/usr/bin/env python3

import os
import sys
import time
from pprint import pprint


# Import the generated SDK
try:
    from webshop_orders_api_client import Client
except ImportError as e:
    print(e)
    print("SDK not found. Make sure you've installed the generated SDK package.")
    sys.exit(1)

def main():
    print("Webshop Orders API Client Example")
    print("=================================")
    
    # Configure the API client
    config = Configuration(
        host="http://localhost:8080",  # Base URL of your API server
    )
    
    # Create an instance of the API client
    with ApiClient(config) as api_client:
        # Create an instance of the Orders API
        orders_api = OrdersApi(api_client)
        
        # Example 1: Get all orders
        print("\n1. Getting all orders...")
        try:
            orders = orders_api.get_all_orders()
            print(f"Found {len(orders)} orders:")
            for order in orders:
                print(f"  - ID: {order.id}, Product: {order.product_name}, "
                      f"Price: ${order.price}, Customer: {order.customer_name}")
        except ApiException as e:
            print(f"Error retrieving orders: {e}")
        
        # Example 2: Get a specific order
        # First, we'll get the ID of an existing order
        print("\n2. Getting a specific order...")
        try:
            first_order_id = orders[0].id if orders else None
            if first_order_id:
                order = orders_api.get_order_by_id(first_order_id)
                print(f"Order details for ID {first_order_id}:")
                print(f"  - Product: {order.product_name}")
                print(f"  - Price: ${order.price}")
                print(f"  - Customer: {order.customer_name}")
            else:
                print("No orders found to retrieve.")
        except ApiException as e:
            print(f"Error retrieving order: {e}")
        
        # Example 3: Create a new order
        print("\n3. Creating a new order...")
        try:
            new_order = Order(
                product_name="Python Book",
                price=29.99,
                customer_name="Python Developer"
            )
            created_order = orders_api.create_order(new_order)
            print(f"Created new order with ID: {created_order.id}")
            print(f"  - Product: {created_order.product_name}")
            print(f"  - Price: ${created_order.price}")
            print(f"  - Customer: {created_order.customer_name}")
        except ApiException as e:
            print(f"Error creating order: {e}")
        
        # Example 4: Update an order
        print("\n4. Updating an order...")
        try:
            if created_order:
                # Update the order we just created
                updated_info = Order(
                    product_name="Advanced Python Book",
                    price=39.99,
                    customer_name=created_order.customer_name
                )
                updated_order = orders_api.update_order(created_order.id, updated_info)
                print(f"Updated order with ID: {updated_order.id}")
                print(f"  - Product: {updated_order.product_name} (was: {created_order.product_name})")
                print(f"  - Price: ${updated_order.price} (was: ${created_order.price})")
                print(f"  - Customer: {updated_order.customer_name}")
            else:
                print("No order created to update.")
        except ApiException as e:
            print(f"Error updating order: {e}")
        
        # Example 5: Delete an order
        print("\n5. Deleting an order...")
        try:
            if created_order:
                orders_api.delete_order(created_order.id)
                print(f"Order with ID {created_order.id} has been deleted.")
                
                # Verify deletion by trying to get the deleted order
                try:
                    orders_api.get_order_by_id(created_order.id)
                except ApiException as e:
                    if e.status == 404:
                        print("Verification successful: Order no longer exists.")
                    else:
                        print(f"Unexpected error during verification: {e}")
            else:
                print("No order created to delete.")
        except ApiException as e:
            print(f"Error deleting order: {e}")
        
        # Example 6: Error handling - trying to get a non-existent order
        print("\n6. Demonstrating error handling...")
        try:
            non_existent_id = "non-existent-id"
            orders_api.get_order_by_id(non_existent_id)
            print("This should not print as an exception should be raised.")
        except ApiException as e:
            print(f"Expected error: {e.status} {e.reason}")
            if hasattr(e, "body"):
                print(f"Response body: {e.body}")

if __name__ == "__main__":
    main()
