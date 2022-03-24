package com.example.onlineshop.activities

object Constants {
    const val BASE_URL ="https://psmobitech.com/myshop/index.php/"
    const val IMAGE_URL ="https://psmobitech.com/myshop/images/"

    const val PREF_NAME = "login_details"



    const val DB_NAME = "ShopDB"
    const val DB_VERSION = 1
    const val CREATE_PRODUCT_TABLE = """
        CREATE TABLE product(
            product_id INTEGER PRIMARY KEY AUTOINCREMENT,
            product_name TEXT,
            price FLOAT,
            qty INTEGER

        )
    """
}