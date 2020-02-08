## Getting Started

These instructions will get you a copy of the project up and running on your local machine.

### Prerequisites

What things you need to install the software and how to install them

```
jdk 1.8
maven
git
```

### Installing

To Install project follow the below steps:

Open Command prompt and run the following command to checkout the project.

```
git clone https://github.com/Urja/PlanGenerator.git
 ```
  Go to project directory 
  
 ```
 cd PlanGenerator
 ```
Now build and run application by using following command

```
mvn clean install
```

Then run following command on Terminal to run the application.

```
mvn spring-boot:run
```

Open rest client and type following url 
```
localhost:{port}/planGenerator/generate-plan
```

method ``POST``

Body
```
{
"loanAmount": "5000",
"nominalRate": "5.0",
"duration": 24,
"startDate": "2018-01-01T00:00:01Z"
}
```

Now check the response code 200 and response data.

