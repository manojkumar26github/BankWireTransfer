--table create script 
 USE [Bank]
GO

/****** Object:  Table [dbo].[BANK_ACCOUNT]    Script Date: 7/5/2021 5:37:26 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[BANK_ACCOUNT](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Customer_name] [varchar](255) NOT NULL,
	[Current_Balance] [float] NOT NULL,
	[DateTimestamp] [datetime] NULL,
 CONSTRAINT [PK_Customer_Idbankwiretransfer] PRIMARY KEY CLUSTERED 
(
	[Customer_name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[BANK_ACCOUNT] ADD  DEFAULT (getdate()) FOR [DateTimestamp]
GO


USE [Bank]
GO

/****** Object:  Table [dbo].[TRANSACTIONS]    Script Date: 7/5/2021 5:37:35 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[TRANSACTIONS](
	[Transaction_id] [int] IDENTITY(1,1) NOT NULL,
	[Customer_name] [varchar](255) NOT NULL,
	[Beneficiary_name] [varchar](255) NOT NULL,
	[Amount] [float] NOT NULL,
	[Transaction_Type] [varchar](255) NOT NULL,
	[DateTimestamp] [datetime] NULL,
PRIMARY KEY CLUSTERED 
(
	[Transaction_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[TRANSACTIONS] ADD  DEFAULT (getdate()) FOR [DateTimestamp]
GO

ALTER TABLE [dbo].[TRANSACTIONS]  WITH CHECK ADD FOREIGN KEY([Customer_name])
REFERENCES [dbo].[BANK_ACCOUNT] ([Customer_name])
GO


select * from BANK_ACCOUNT with (nolock)
select * from TRANSACTIONS with (nolock);



INSERT INTO BANK_ACCOUNT (Customer_name,Current_Balance)
VALUES ('Steve',10000);
INSERT INTO BANK_ACCOUNT (Customer_name,Current_Balance)
VALUES ('Adam',10000);
INSERT INTO BANK_ACCOUNT (Customer_name,Current_Balance)
VALUES ('Tom',10000);

 INSERT INTO TRANSACTIONS (Customer_name,Beneficiary_name,Amount,Transaction_Type)
VALUES ('Steve','Adam',100,'Debit');

select * from BANK_ACCOUNT with (nolock)
select * from TRANSACTIONS with (nolock);

 --DELETE FROM BANK_ACCOUNT
 --DBCC CHECKIDENT('BANK_ACCOUNT', RESEED, 0)

 --DELETE FROM TRANSACTIONS
 --DBCC CHECKIDENT('TRANSACTIONS', RESEED, 0)
