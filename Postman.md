# Post query localhost:8081/

Person
localhost:8081/people
{
"name": "Mari",
"lastName": "Minna",
"yearOfBirth": 1993,
"phoneNumber": "89002225569",
"email": "mannaa@gmail.com",
"username": "manna",
"password":"mannamanna",
"discount":5,
"role":1
}

{
"name": "Mari",
"lastName": "Minna",
"yearOfBirth": 1993,
"phoneNumber": "89002225569",
"email": "mannaa@gmail.com",
"username": "manna",
"password": "$2a$10$mXEFaVyrbQELW1H.H0IDwuq3.EDYzDGYzQANmVmeHKwq5j/A6fxvW"
}

{
"name": "Ignat",
"lastName": "Kupryashin",
"yearOfBirth": 1985,
"phoneNumber": "89153007949",
"email": "kupryashin@gmail.com",
"username": "ignatkupryashin",
"password":"secretpassword",
"discount":5,
"role":2
}
{
"name": "Ignat",
"lastName": "Kupryashin",
"yearOfBirth": 1985,
"phoneNumber": "89153007949",
"email": "kupryashin@gmail.com",
"username": "ignatkupryashin",
"password": "$2a$10$22qb7NQGkriyfX/HFEQN5utLbWJYxKo.owYefJGDFi/eTxjLl2QBO"
}


Role
{
"name":"User",
"roleValue":"ROLE_USER"
}

Discount
localhost:8081/discount
POST request
{
"name": "twenty",
"sale":"TWENTY"
}

RestaurantReviews
localhost:8081/restReviews
{
"owner":3,
"gradle":5,
"comment":"this final restaurant review"
}

StateFromTable
localhost:8081/state_from_tables
{

"name":"paid",
"value":"PAID_THE_BILL"

}

ReserveTable
localhost:8081/reserve_a_table
{
"accommodationNumber":2,
"stateFromTable":1,
"numberOfSeats":6
}

Order
localhost:8081/orders
{
"owner":3,
"price":5000.00,
"statusFromOrder":"CLOSED_SUCCESSFUL_ORDER"
}

TableReservation
localhost:8081/table-reservations
{
"table":1,
"owner":3,
"date":"2024-01-04T21:56:12.991310",
"numberOfGuests":5,
"authorThisRecords":3
}

Dishes
localhost:8081/dishes
{
"name":"Pasta",
"price":540.00,
"weight":300.00,
"calories":700,
"proteins":30,
"fats":200,
"carbohydrates":50,
"imageURL":"https://klike.net/uploads/posts/2022-09/1662462025_j-15.jpg"
}

OrderElements
localhost:8081/order-element
{
"orderId":1,
"dishesId":1,
"quantity":1
}

FoodReviews
localhost:8081/food-reviews
{
"author":3,
"dishes":1,
"grade":4,
"comment":"Очень хорошее мясо"
}

Ingredients
localhost:8081/ingredients
{
"ingredientName":"Tomato",
"remnant":200,
"description":"Red Italiano Tomato",
"isVegan":true,
"isSpicy":false
}

UnitsOfMeasurement
localhost:8081/unit-of-measurements
{
"name":"KILOGRAMMES",
"unitOfMeasurement":"KILOGRAMMES",
"commentary":"килограмм, 1 кг = 1000 гр."
}

Composition of dishes
localhost:8081/composition_of_dishes
{
"name":"begetable red",
"ingredient":1,
"count":10,
"units":2
}