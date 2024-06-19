## API Reference

#### Create an account

```http
  POST http://localhost:8081/accounts
```

| Parameter | Type     | 
| :-------- | :------- |
| `name` | `string` | 
| `email` | `string` |
| `openingAmount` | `number` | 

#### Get account information

```http
  GET http://localhost:8081/accounts/${id}
```
