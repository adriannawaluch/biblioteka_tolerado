USE [biblioteka_tolerado]
GO
/****** Object:  User [adriannawaluch]    Script Date: 14.01.2024 17:44:45 ******/
CREATE USER [adriannawaluch] FOR LOGIN [adriannawaluch] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_accessadmin] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_securityadmin] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_ddladmin] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_backupoperator] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_datareader] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_datawriter] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_denydatareader] ADD MEMBER [adriannawaluch]
GO
ALTER ROLE [db_denydatawriter] ADD MEMBER [adriannawaluch]
GO
/****** Object:  Table [dbo].[Authors]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Authors](
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[authorID] [int] IDENTITY(1,1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[authorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Books]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Books](
	[title] [nvarchar](250) NOT NULL,
	[language] [nvarchar](50) NULL,
	[availability] [int] NOT NULL,
	[bookID] [int] IDENTITY(1,1) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[bookID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BooksAuthors]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BooksAuthors](
	[bookID] [int] NOT NULL,
	[authorID] [int] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Rental]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Rental](
	[loanID] [int] NOT NULL,
	[userID] [int] NOT NULL,
	[loan_date] [date] NOT NULL,
	[return_date] [date] NULL,
	[returned] [bit] NOT NULL,
	[bookID] [int] NULL,
 CONSTRAINT [PK_Rental] PRIMARY KEY CLUSTERED 
(
	[loanID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  UserDefinedFunction [dbo].[borrowed_books_by_author]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE FUNCTION [dbo].[borrowed_books_by_author] (@AuthorID INT)
RETURNS TABLE
AS
RETURN (
    SELECT A.AuthorID,
           A.first_name,
           A.last_name,
           B.BookID,
           B.Title,
           R.loan_date,
           R.return_date,
           R.Returned
    FROM Authors A
    JOIN BooksAuthors BA ON A.AuthorID = BA.AuthorID
    JOIN Books B ON BA.BookID = B.BookID
    JOIN Rental R ON B.BookID = R.BookID
    WHERE A.AuthorID = @AuthorID
);
GO
/****** Object:  Table [dbo].[Passwords]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Passwords](
	[userID] [int] NOT NULL,
	[Password] [nvarchar](50) NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[first_name] [nvarchar](50) NOT NULL,
	[last_name] [nvarchar](50) NOT NULL,
	[email] [nvarchar](50) NOT NULL,
	[userID] [int] IDENTITY(1,1) NOT NULL,
	[user_login] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Books] ADD  CONSTRAINT [DF_Rental_availability]  DEFAULT ((1)) FOR [availability]
GO
ALTER TABLE [dbo].[Users] ADD  CONSTRAINT [DF_Users_Login]  DEFAULT ('') FOR [user_login]
GO
ALTER TABLE [dbo].[BooksAuthors]  WITH CHECK ADD  CONSTRAINT [FK_BooksAuthors_Authors] FOREIGN KEY([authorID])
REFERENCES [dbo].[Authors] ([authorID])
GO
ALTER TABLE [dbo].[BooksAuthors] CHECK CONSTRAINT [FK_BooksAuthors_Authors]
GO
ALTER TABLE [dbo].[BooksAuthors]  WITH CHECK ADD  CONSTRAINT [FK_BooksAuthors_Books] FOREIGN KEY([bookID])
REFERENCES [dbo].[Books] ([bookID])
GO
ALTER TABLE [dbo].[BooksAuthors] CHECK CONSTRAINT [FK_BooksAuthors_Books]
GO
ALTER TABLE [dbo].[Passwords]  WITH CHECK ADD  CONSTRAINT [FK_Passwords_Users] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[Passwords] CHECK CONSTRAINT [FK_Passwords_Users]
GO
ALTER TABLE [dbo].[Rental]  WITH CHECK ADD FOREIGN KEY([bookID])
REFERENCES [dbo].[Books] ([bookID])
GO
ALTER TABLE [dbo].[Rental]  WITH CHECK ADD  CONSTRAINT [FK_Rental_Users] FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[Rental] CHECK CONSTRAINT [FK_Rental_Users]
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD  CONSTRAINT [CHK_ValidEmail] CHECK  (([Email] like '%_@_%.__%'))
GO
ALTER TABLE [dbo].[Users] CHECK CONSTRAINT [CHK_ValidEmail]
GO
/****** Object:  StoredProcedure [dbo].[add_book]    Script Date: 14.01.2024 17:44:45 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[add_book]
    @Title NVARCHAR(255),
    @author_first_name NVARCHAR(100),
    @author_last_name NVARCHAR(100)
AS
BEGIN
    DECLARE @authorID INT;

    -- Sprawdzamy, czy autor już istnieje
    SELECT @authorID = authorID
    FROM Authors
    WHERE first_name = @author_first_name AND last_name = @author_last_name;

    -- Jeśli autor nie istnieje, dodajemy go
    IF @authorID IS NULL
    BEGIN
        INSERT INTO Authors (first_name, last_name)
        VALUES (@author_first_name, @author_last_name);

        SET @authorID = SCOPE_IDENTITY(); -- Pobieramy ID ostatnio dodanego autora
    END;

    -- Sprawdzamy, czy książka już istnieje
    IF EXISTS (
        SELECT 1
        FROM BooksAuthors BA
        JOIN Books B ON BA.BookID = B.BookID
        WHERE B.Title = @Title AND BA.authorID = @authorID
    )
    BEGIN
        -- Jeśli książka już istnieje, zwiększamy availability w tabeli Books
        UPDATE Books
        SET Availability = Availability + 1
        WHERE Title = @Title;
    END
    ELSE
    BEGIN
        -- Jeśli książka nie istnieje, dodajemy nową książkę
        INSERT INTO Books (Title, Availability)
        VALUES (@Title, 1);
    END;

    -- Dodajemy wpis do BooksAuthors
    INSERT INTO BooksAuthors (BookID, authorID)
    VALUES (
        (SELECT BookID FROM Books WHERE Title = @Title),
        @authorID
    );
END;
GO
