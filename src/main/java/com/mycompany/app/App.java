/*----------------------------------------------------------------------------------------
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See LICENSE in the project root for license information.
 *---------------------------------------------------------------------------------------*/

package com.mycompany.app;

import spark.Spark;

import static spark.Spark.get;

public class App {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello, world!");
    }
}
