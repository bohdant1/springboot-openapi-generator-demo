# Python SDK Example Scripts

This directory contains example scripts demonstrating how to use the Python SDK generated from the Webshop Orders API.

## Prerequisites

1. The Spring Boot application should be running locally on port 8080
2. The Python SDK should be installed:
   ```bash
   cd path/to/python-sdk/webshop-orders-api-client
   pip install .
   ```

## Running the Examples

To run the example script:

```bash
cd python-examples
python example_script.py
```

## What the Example Script Does

The script demonstrates:

1. **Initializing the SDK client** - Shows how to configure and create a client instance
2. **Getting all orders** - Retrieves and displays all orders
3. **Getting a specific order** - Retrieves an order by ID
4. **Creating a new order** - Creates a new order with sample data
5. **Updating an order** - Updates an existing order with new information
6. **Deleting an order** - Deletes an order and verifies it was removed
7. **Error handling** - Demonstrates how to handle API errors using try/except blocks

## Customizing the Examples

You can modify the example script to fit your specific needs:

- Change the server URL in the `Configuration` setup if your API is hosted elsewhere
- Modify the order data to match your own test data
- Add authentication if your API requires it
