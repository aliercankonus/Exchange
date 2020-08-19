## **API Documentation:**

http://localhost:8080/exchange/api-docs.html

#### **General Info:**

3 endpoints are ready for use.

To get exchange rates:
base and symbols parameters are optional.
**/exchange/rate?base=USD&symbols=EUR,GBP**
 
To calculate amount in target currency:
from, to and amount parameters are mandatory.
**/exchange/convert?from=USD&to=EUR&amount=100**

To list conversions:
At least one of the transactionId or date must be provided.
**/exchange/list?date=19-08-2020&transactionId=cb78461f-cbfc-4f39-8ac9-06e86b388634**

In mem database H2 is used and 
**http://localhost:8080/exchange/h2** is the url for H2 console

Lombok is used in this project.
