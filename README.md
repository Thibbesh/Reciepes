# Read Me First
As per the given Requirement, I have made below architectural choices.

### Architectural choice

1. Application must be production ready.
    * I have chosen Springboot.
2. REST application must be implemented using Java.
    * I have chosen Java 8 
3. Data must be persisted in a database.
    * I have chosen com.h2database.
4. Use any frameworks of your choice for REST.
    * I have chosen spring-boot-starter-web
5. Unit testing must be taken in due consideration.
    * I have chosen spring-boot-starter-test, org.junit.platform, 
6. Describe at least 10 testing scenarios using GivenWhenThen style.
    * Cucumber Test - Automate Gherkin Scenarios(find recipes.feature in test package)
7. The API's must be built ensuring that it is secured from security attacks.
    * I have chosen spring-boot-starter-security
    * all the request authenticated/protected by password.
    * CORS filter configuration
    * Disabled Cross Site Request Forgery (CSRF) OWASP foundation
    * Disabled x-frame-options 
    
Bonus 
1. REST application should be secured by implementing authentication process (please provide 
credentials).
    * All endpoints request authenticated(please find user name and password in SecurityConfig.java) 
2. Application should have an API documentation.
    * I have chosen springdoc-openapi-ui
3. Write automation tests for the described testing scenarios.
   * No(Not implemented) 
4. Use of container based solutions is an added advantage.
   * No(Not implemented)
5. Creating a single-page application illustrating the use of API.
   * No(Not implemented)


### Technologies and frameworks.
 1. Java 1.8
 2. Maven(apache-maven-3.6.1)
 3. Springboot 2.4.5
 4. ORM spring-boot-starter-data-jpa
 5. Database: com.h2database
 6. springdoc-openapi-ui 1.5.9
 7. junit-platform-runner 1.6.0
 
### recipes-web application start up
 * checkout the from github URL: 
 * run the maven command mvn clean install
 * start RecipesWebApplication.java
 * Context path "/" and port is 8080
 * Access "Get" end point,  for ex: http://localhost:8080/recipe
 * User Name: admin, user
 * password: admin, user
 
### api-docs, swagger-ui
* http://localhost:8080/v3/api-docs/
* http://localhost:8080/swagger-ui/index.html
* /v3/api-docs(search for /v3/api-docs on explore search bar, then will get recipes-web endpoints)



### Database connections, user name and password
* http://localhost:8080/h2-console/
   ###### Driver Class: org.h2.Driver
   ###### JDBC URL: jdbc:h2:./src/main/resources/db/demodb
   ###### in case if would like to use in-memory jdbc:h2:mem:testdb
   ###### User Name: admin
   ###### Password: admin
   
### recipes-web endpoints
##### POST endpoint
* /POST recipeDto schemas 
* via postman: Authorization 
    * Select Authorization tab,
    * Select Type as Basic Auth
    * Enter User name as "admin"
    * Enter Password as "admin"
    * Click on Update Request   
   
* http://localhost:8080/recipe
* In the Body 
    * Choose raw radio button
    * Choose as JSON(application/json)
* Provide below RecipeDTO schemas in the body(Created: DateTime will generate LocalDateTime.now(), not required to pass on JSON)
* {
        "name": "Pizza",
        "servings": 10,
        "vegetarian": true,
        "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
        "ingredients": [
        	{
        		"name":"onion",
        		"amount":"100"
        		
        	},
        	{
        		"name":"Tomoto",
        		"amount":"400"
        		
        	},
        	{
        		"name":"wheat floor",
        		"amount":"200 kg"
        		
        	},
        	{
        		"name":"mushroom",
        		"amount":"100 kg"
        		
        	},
        	{
        		"name":"Chicken",
        		"amount":"100 kg"
        		
        	}]
    }
* Response from recipes-web : Recipe saved successfully..    

##### GETALL endpoint    
* /GET
* via postman: Authorization 
    * Select Authorization tab,
    * Select Type as Basic Auth
    * Enter User name as "admin"
    * Enter Password as "admin"
    * Click on Update Request 
    
* http://localhost:8080/recipe 
* Response
* [
    {
        "id": 33,
        "name": "Donor Kebab",
        "servings": 5,
        "created": "25‐05‐2021 22:27",
        "vegetarian": true,
        "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
        "ingredients": [
            {
                "ingredientId": 44,
                "name": "onion",
                "amount": "4"
            },
            {
                "ingredientId": 45,
                "name": "Tomoto",
                "amount": "6"
            },
            {
                "ingredientId": 46,
                "name": "wheat floor",
                "amount": "27 kg"
            },
            {
                "ingredientId": 47,
                "name": "mushroom",
                "amount": "100 kg"
            },
            {
                "ingredientId": 48,
                "name": "Chicken",
                "amount": "100 kg"
            }
        ]
    },
    {
        "id": 34,
        "name": "Chicken Biriyani",
        "servings": 10,
        "created": "25‐05‐2021 22:24",
        "vegetarian": true,
        "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
        "ingredients": [
            {
                "ingredientId": 34,
                "name": "onion",
                "amount": "100"
            },
            {
                "ingredientId": 35,
                "name": "Tomoto",
                "amount": "400"
            },
            {
                "ingredientId": 36,
                "name": "wheat floor",
                "amount": "200 kg"
            },
            {
                "ingredientId": 37,
                "name": "mushroom",
                "amount": "100 kg"
            },
            {
                "ingredientId": 38,
                "name": "Chicken",
                "amount": "100 kg"
            }
        ]
    },
    {
        "id": 65,
        "name": "Pizza",
        "servings": 10,
        "created": "25‐05‐2021 22:43",
        "vegetarian": true,
        "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
        "ingredients": [
            {
                "ingredientId": 65,
                "name": "onion",
                "amount": "100"
            },
            {
                "ingredientId": 66,
                "name": "Tomoto",
                "amount": "400"
            },
            {
                "ingredientId": 67,
                "name": "wheat floor",
                "amount": "200 kg"
            },
            {
                "ingredientId": 68,
                "name": "mushroom",
                "amount": "100 kg"
            },
            {
                "ingredientId": 69,
                "name": "Chicken",
                "amount": "100 kg"
            }
        ]
    },
    {
        "id": 66,
        "name": "French Fries",
        "servings": 10,
        "created": "25‐05‐2021 22:52",
        "vegetarian": true,
        "instructions": "keep ready with below ingredients and take pan, turn on stove. wash your hands with soap",
        "ingredients": [
            {
                "ingredientId": 70,
                "name": "Onion",
                "amount": "2 kg"
            },
            {
                "ingredientId": 71,
                "name": "Chicken",
                "amount": "10 kg"
            },
            {
                "ingredientId": 72,
                "name": "Potato",
                "amount": "20 kg"
            }
        ]
    },
    {
        "id": 67,
        "name": "Pizza",
        "servings": 10,
        "created": "25‐05‐2021 23:13",
        "vegetarian": true,
        "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
        "ingredients": [
            {
                "ingredientId": 73,
                "name": "onion",
                "amount": "100"
            },
            {
                "ingredientId": 74,
                "name": "Tomoto",
                "amount": "400"
            },
            {
                "ingredientId": 75,
                "name": "wheat floor",
                "amount": "200 kg"
            },
            {
                "ingredientId": 76,
                "name": "mushroom",
                "amount": "100 kg"
            },
            {
                "ingredientId": 77,
                "name": "Chicken",
                "amount": "100 kg"
            }
        ]
    },
    {
        "id": 68,
        "name": "Pizza",
        "servings": 10,
        "created": "26‐05‐2021 23:17",
        "vegetarian": true,
        "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
        "ingredients": [
            {
                "ingredientId": 78,
                "name": "onion",
                "amount": "100"
            },
            {
                "ingredientId": 79,
                "name": "Tomoto",
                "amount": "400"
            },
            {
                "ingredientId": 80,
                "name": "wheat floor",
                "amount": "200 kg"
            },
            {
                "ingredientId": 81,
                "name": "mushroom",
                "amount": "100 kg"
            },
            {
                "ingredientId": 82,
                "name": "Chicken",
                "amount": "100 kg"
            }
        ]
    },
    {
        "id": 69,
        "name": "Pizza",
        "servings": 10,
        "created": "26‐05‐2021 23:17",
        "vegetarian": true,
        "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
        "ingredients": [
            {
                "ingredientId": 83,
                "name": "onion",
                "amount": "100"
            },
            {
                "ingredientId": 84,
                "name": "Tomoto",
                "amount": "400"
            },
            {
                "ingredientId": 85,
                "name": "wheat floor",
                "amount": "200 kg"
            },
            {
                "ingredientId": 86,
                "name": "mushroom",
                "amount": "100 kg"
            },
            {
                "ingredientId": 87,
                "name": "Chicken",
                "amount": "100 kg"
            }
        ]
    }
]
##### GET endpoint   
* via postman: Authorization 
    * Select Authorization tab,
    * Select Type as Basic Auth
    * Enter User name as "admin"
    * Enter Password as "admin"
    * Click on Update Request 
    
* http://localhost:8080/recipe/33

* Response from recipes-web:
* {
    "id": 33,
    "name": "Donor Kebab",
    "servings": 5,
    "created": "25‐05‐2021 22:27",
    "vegetarian": true,
    "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
    "ingredients": [
        {
            "ingredientId": 44,
            "name": "onion",
            "amount": "4"
        },
        {
            "ingredientId": 45,
            "name": "Tomoto",
            "amount": "6"
        },
        {
            "ingredientId": 46,
            "name": "wheat floor",
            "amount": "27 kg"
        },
        {
            "ingredientId": 47,
            "name": "mushroom",
            "amount": "100 kg"
        },
        {
            "ingredientId": 48,
            "name": "Chicken",
            "amount": "100 kg"
        }
    ]
}

##### PUT endpoint   
* via postman: Authorization 
    * Select Authorization tab,
    * Select Type as Basic Auth
    * Enter User name as "admin"
    * Enter Password as "admin"
    * Click on Update Request 
    
* http://localhost:8080/recipe/33  

* In the Body 
    * Choose raw radio button
    * Choose as JSON(application/json)
    
* Provide below RecipeDTO schemas in the body(Created: DateTime will generate LocalDateTime.now(), not required to pass on JSON)    
* {
    "name": "Donor Kebab",
    "servings": 5,
    "vegetarian": true,
    "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
    "ingredients": [
        {
            "name": "onion",
            "amount": "4"
        },
        {
            "name": "Tomoto",
            "amount": "6"
        },
        {
            "name": "wheat floor",
            "amount": "27 kg"
        },
        {
            "name": "mushroom",
            "amount": "100 kg"
        },
        {
            "name": "Chicken",
            "amount": "100 kg"
        }
    ]
}
* Response from Recipes-web: 
* {
    "id": 33,
    "name": "Donor Kebab",
    "servings": 5,
    "created": "26‐05‐2021 23:21",
    "vegetarian": true,
    "instructions": "1. Heat oven to 190C/170C fan/gas 5. Grease and flour two 20cm sandwich tins. 2. Place 200g softened unsalted butter, 200g caster sugar and 1 tsp vanilla extract into a bowl and beat well to a creamy consistency. 3. Slowly beat in 4 medium eggs, one by one, then fold in 200g self-raising flour and mix well. 4. Divide the mix between the cake tins, place into the oven and bake for about 20 mins until risen and golden brown. The cakes should spring back when gently pushed in the middle. 5. When ready, remove from the oven and allow to cool for 5 mins in the tin, before turning out onto a wire rack and cooling completely. 6. Spread about 6 tbsp raspberry jam onto one cake and top with 250ml whipped double cream. Sandwich the cakes together and dust with icing sugar.",
    "ingredients": [
        {
            "ingredientId": 88,
            "name": "onion",
            "amount": "4"
        },
        {
            "ingredientId": 89,
            "name": "Tomoto",
            "amount": "6"
        },
        {
            "ingredientId": 90,
            "name": "wheat floor",
            "amount": "27 kg"
        },
        {
            "ingredientId": 91,
            "name": "mushroom",
            "amount": "100 kg"
        },
        {
            "ingredientId": 92,
            "name": "Chicken",
            "amount": "100 kg"
        }
    ]
} 

##### DELETE endpoint   
* http://localhost:8080/recipe/33
* Response from recipes-web : Recipe deleted successfully..
