# Post query localhost:8080/
Person
localhost:8080/people/add
{
"name": "Mari",
"lastName": "Minna",
"yearOfBirth": 1993,
"phoneNumber": "89002225569",
"email": "mannaa@gmail.com",
"username": "manna",
"password":"mannamanna",
}
"discount":5,
"role":1

Role
{
"name":"User",
"roleValue":"ROLE_USER"
}

Discount
localhost:8080/discount/add
POST request
{
"name": "twenty",
"sale":"TWENTY"
}

RestaurantReviews
localhost:8080/restReviews/add
{
"owner":3,
"gradle":5,
"comment":"this final restaurant review"
}


StateFromTable
localhost:8080/state_from_tables/add
{

"name":"paid",
"value":"PAID_THE_BILL"

}


ReserveTable
localhost:8080/reserve_a_table/all
{
"accommodationNumber":2,
"stateFromTable":1,
"numberOfSeats":6
}

Order
localhost:8080/orders/add
{
"owner":3,
"price":5000.00,
"statusFromOrder":"CLOSED_SUCCESSFUL_ORDER"
}

Dishes
localhost:8081/dishes/add
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