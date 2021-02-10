# ratingo

### **API Details:**

1.	**Create Product**:
      **Type**:				POST
      **URL**:				/product
      **Request Payload**:	{
      "name": "COVID-19",
      "description": "Track Your COVID-19 Status !"
      }
      **Response Payload**:	{
      "id": 5,
      "name": "COVID-19",
      "description": "Track Your COVID-19 Status !",
      "ratingDO": {
      "id": 6,
      "totalRating": 0,
      "ratingCount": 0,
      "averageRating": 0
      }
      }

2. Get Product
   **Type**:				GET
   **URL**:				/product?id
   **Request Payload**:	NONE
   **Response Payload**:	{
   "id": 1,
   "name": "Lab Test Order",
   "description": "No idea what it is !",
   "ratingDO": {
   "id": 2,
   "totalRating": 0,
   "ratingCount": 0,
   "averageRating": 0.000000
   }
   }

3. **Get Products**
   **Type**:				GET
   **URL**:				/product/findAll
   **Request Payload**:	NONE
   **Response Payload**:	[
   {
   "id": 1,
   "name": "Lab Test Order",
   "description": "No idea what it is !",
   "ratingDO": {
   "id": 2,
   "totalRating": 0,
   "ratingCount": 0,
   "averageRating": 0.000000
   }
   },
   {
   "id": 2,
   "name": "Mindfulness",
   "description": "Is it full of mind !",
   "ratingDO": {
   "id": 3,
   "totalRating": 11,
   "ratingCount": 3,
   "averageRating": 3.666667
   }
   },
   ...
   ]

4. **Create User**
   **Type**:				POST
   **URL**:				/user
   **Request Payload**:	{
   "firstName" : "Rajesh",
   "lastName" : "Shankar",
   "email" : "rajesh.shankar000@yahoo.com",
   "mobNo" : "7967664210"
   }
   **Response Payload**:	{
   "id": 3,
   "firstName": "Rajesh",
   "lastName": "Shankar",
   "email": "rajesh.shankar000@yahoo.com",
   "mobNo": "7967664210"
   }


5. **Enroll User**
   **Type**:				POST
   **URL**:				/user/enroll
   **Request Payload**:	{
   "productId" : 2,
   "userId" : 1
   }
   **Response Payload**:	{
   "id": 4,
   "userId": 1,
   "productId": 2,
   "startDate": "2021-02-09",
   "endDate": null,
   "active": true
   }


6. **Rate Product**
   **Type**:				PUT
   **URL**:				/rate
   **Request Payload**:	{
   "enrollmentId" : 4,
   "ratedValue" : 4
   }
   **Response Payload**:	NONE


### **Functionality & Assumptions** :

1. Provided an API for creating product, user can create any number of product using given API.
2. User Needs to Register Using His email id and phone no. If user already registered then he is not allowed to register again.
3. Next User can choose from any valid product to get enrolled with.Once User gets enrolled his enrollment is marked as active with that product and he can not enroll again with same prodcut till previous enrollment is active.
4. After enrollment is over user can rate the product, and enrollment will be marked as end and inactive. Now user can not rate it again.

