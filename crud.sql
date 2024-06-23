/****** Script for SelectTopNRows command from SSMS  ******/
SELECT TOP (1000) [account]
      ,[pass]
      ,[lastName]
      ,[firstName]
      ,[birthday]
      ,[gender]
      ,[phone]
      ,[isUse]
      ,[roleInSystem]
  FROM [ProductIntro].[dbo].[accounts]

select lastName + firstName as fullname from accounts

SELECT account, lastName, firstName, birthday, gender, phone, roleInSystem FROM accounts


UPDATE accounts
SET account='ad', pass='ad', lastName='Cường', firstName='Trần', 
birthday='2024-05-26 00:00:00.000', gender=0, 
phone='0867016729', isUse=1, roleInSystem=1
WHERE account='ad'