docker exec -it postgres psql -U postgres -d cool -c "CREATE TABLE IF NOT EXISTS Genre (
    genre_type VARCHAR(255) UNIQUE NOT NULL PRIMARY KEY
);"


docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO Genre (genre_type) VALUES
('scifi'),
('fantasy'),
('adventure'),
('mystery'),
('biography'),
('manga'),
('music'),
('sports'),
('romance')
ON CONFLICT (genre_type) DO NOTHING;";

docker exec -it postgres psql -U postgres -d cool -c "CREATE TABLE IF NOT EXISTS AgeRange (
    id SERIAL PRIMARY KEY,
    ageClass VARCHAR(255) UNIQUE NOT NULL
);"

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO AgeRange (ageClass, ageClass) VALUES
('child'),
('teen'),
('young_adult'),
('adult'),
('elder')
ON CONFLICT (ageClass) DO NOTHING;";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO publisher (name, email, logo_url, site_url) VALUES
('TechBooks Publishing', 'contact@techbooks.com', 'https://miro.medium.com/v2/resize:fit:1000/1*HruntcbNU6LZeHIqfmwI8A.png', 'https://www.techbooks.com'),
('Health and Wellness Press', 'info@healthwellnesspress.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTMFzyRWWqLqoteWaUTp_vkMWqdrxf89heUfQ&s', 'https://www.healthwellnesspress.com'),
('History Matters', 'support@historymatters.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTtSKmjNx7gvmb3xFMFeQ6knhKE_ERQQrVc6w&s', 'https://www.historymatters.com'),
('Future Fiction House', 'hello@futurefictionhouse.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQxMUoEieRq59SaRFn4fBsrK5jQsa5KRmG3Ww&s', 'https://www.futurefictionhouse.com'),
('Green Earth Publishing', 'admin@greenearthpub.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRmCvMC7M1daPIP_G0sGAnFksl8J2htPdu9QQ&s', 'https://www.greenearthpub.com'),
('EduTech Publications', 'editor@edutechpub.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSAwr9A1jGpVfvkGMDKjnwNfju5X0DSVP_UqQ&s', 'https://www.edutechpub.com'),
('Science Today Press', 'contact@sciencetodaypress.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQpWIU6byX7W31J-dMGtwE9oxs0Hp2wRUKchg&s', 'https://www.sciencetodaypress.com'),
('Artisan Books', 'info@artisanbooks.com', 'https://m.media-amazon.com/images/S/amzn-author-media-prod/dlm1odvg77rmlj0bhas7sh0drh._SY450_CR0%2C0%2C450%2C450_.jpg', 'https://www.artisanbooks.com'),
('Culinary Creations Press', 'support@culinarycreationspress.com', 'https://culinarycreationsonline.com/wp-content/uploads/2019/01/txt_burgundy_nc_color.jpg', 'https://www.culinarycreationspress.com'),
('Mindful Living Publications', 'hello@mindfullivingpub.com', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQGEFHuZ1C0Ok8pDTiSFkQ5HoE-iM9EhM4rXQ&s', 'https://www.mindfullivingpub.com');";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO customer (role, username, birth_date, country, description, email, gender, interests, name, password, profile_banner_url, profile_image_url, pronouns,highlighted_bookshelf_name) VALUES
('customer', 'techguru', '1985-04-12', 'USA', 'Tech enthusiast and blogger', 'techguru@example.com', 'male', 'Technology, Gadgets, Coding', 'John Doe', '\$2a\$10\$EhVmXw6N4PLMGQa0t0BchuXA1y0VoJLn8szghkGkRiFUvHoopcyNy', 'https://example.com/banners/techguru.jpg', 'https://randomuser.me/api/portraits/men/1.jpg', 'he/him', 'bookshelf1'),
('author', 'randallmunroe', '1988-06-15', 'Germany', 'Environmental activist and blogger', 'greenearth@example.com', 'female', 'Environment, Sustainability, Nature', 'Sophia Müller', '\$2a\$10\$EhVmXw6N4PLMGQa0t0BchuXA1y0VoJLn8szghkGkRiFUvHoopcyNy', 'https://example.com/banners/greenearth.jpg', 'https://randomuser.me/api/portraits/men/5.jpg', 'she/her', NULL),
('author', 'jkrowling', '1987-09-12', 'Italy', 'Artist and art historian', 'artisanartist@example.com', 'male', 'Art, History, Painting', 'Luca Rossi', '\$2a\$10\$EhVmXw6N4PLMGQa0t0BchuXA1y0VoJLn8szghkGkRiFUvHoopcyNy', 'https://example.com/banners/artisanartist.jpg', 'https://randomuser.me/api/portraits/women/8.jpg', 'she/her', NULL),
('customer', 'wellnessqueen', '1990-07-19', 'Canada', 'Wellness coach and nutritionist', 'wellnessqueen@example.com', 'female', 'Health, Nutrition, Yoga', 'Emily Clark', '\$2a\$10\$EhVmXw6N4PLMGQa0t0BchuXA1y0VoJLn8szghkGkRiFUvHoopcyNy', 'https://example.com/banners/wellnessqueen.jpg', 'https://randomuser.me/api/portraits/women/2.jpg', 'she/her', NULL),
('customer', 'historybuff', '1978-03-25', 'UK', 'History professor and author', 'historybuff@example.com', 'male', 'History, Archaeology, Museums', 'Michael Smith', '\$2a\$10\$EhVmXw6N4PLMGQa0t0BchuXA1y0VoJLn8szghkGkRiFUvHoopcyNy', 'https://example.com/banners/historybuff.jpg', 'https://randomuser.me/api/portraits/men/3.jpg', 'he/him', NULL),
('customer', 'fictionfan', '1992-11-08', 'Australia', 'Avid reader and fiction writer', 'fictionfan@example.com', 'non_binary', 'Literature, Writing, Reading', 'Alex Johnson', '\$2a\$10\$EhVmXw6N4PLMGQa0t0BchuXA1y0VoJLn8szghkGkRiFUvHoopcyNy', 'https://example.com/banners/fictionfan.jpg', 'https://randomuser.me/api/portraits/women/4.jpg', 'they/them', NULL);";


docker exec -it postgres psql -U postgres -d cool -c "ALTER TABLE book ALTER COLUMN description TYPE TEXT;";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO book (isbn, description, image_url, launch_date, title, total_page_numbers, author_username, publisher_name) 
VALUES
('1','Bestselling author and cartoonist Randall Munroe explains how to predict the weather by analyzing the pixels of your Facebook photos. He teaches you how to tell if you are a baby boomer or a 90s kid by measuring the radioactivity of your teeth. He offers tips for taking a selfie with a telescope, crossing a river by boiling it, and powering your house by destroying the fabric of space-time. And if you want to get rid of the book once you are done with it, he walks you through your options for proper disposal, including dissolving it in the ocean, converting it to a vapor, using tectonic plates to subduct it into the Earths mantle, or launching it into the Sun. By exploring the most complicated ways to do simple tasks, Munroe doesnt just make things difficult for himself and his readers. As he did so brilliantly in What If?, Munroe invites us to explore the most absurd reaches of the possible. Full of clever infographics and amusing illustrations, How To is a delightfully mind-bending way to better understand the science and technology underlying the things we do every day.','https://xkcd.com/how-to/howto-cover.png','2019-09-03','How To: Absurd Scientific Advice for Common Real-World Problems',308,'randallmunroe','Science Today Press'),
('2','Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive. Addressed in green ink on yellowish parchment with a purple seal, they are swiftly confiscated by his grisly aunt and uncle. Then, on Harrys eleventh birthday, a great beetle-eyed giant of a man called Rubeus Hagrid bursts in with some astonishing news: Harry Potter is a wizard, and he has a place at Hogwarts School of Witchcraft and Wizardry. An incredible adventure is about to begin!','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1598823299i/42844155.jpg','1997-06-26','Harry Potter and the Sorcerers Stone',333,'jkrowling','Future Fiction House'),
('3','Ever since Harry Potter had come home for the summer, the Dursleys had been so mean and hideous that all Harry wanted was to get back to the Hogwarts School for Witchcraft and Wizardry. But just as hes packing his bags, Harry receives a warning from a strange impish creature who says that if Harry returns to Hogwarts, disaster will strike.','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1474169725i/15881.jpg','1998-07-02','Harry Potter and the Chamber of Secrets',352,'jkrowling','Future Fiction House'),
('4','Harry Potter, along with his best friends, Ron and Hermione, is about to start his third year at Hogwarts School of Witchcraft and Wizardry. Harry cant wait to get back to school after the summer holidays. (Who wouldnt if they lived with the horrible Dursleys?) But when Harry gets to Hogwarts, the atmosphere is tense.','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1630547330i/5.jpg','1999-07-08','Harry Potter and the Prisoner of Azkaban',734,'jkrowling','Future Fiction House'),
('5','It is the summer holidays and soon Harry Potter will be starting his fourth year at Hogwarts School of Witchcraft and Wizardry. Harry is counting the days: there are new spells to be learnt, more Quidditch to be played, and Hogwarts castle to continue exploring.','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1554006152i/6.jpg','2000-07-08','Harry Potter and the Goblet of Fire',734,'jkrowling','Future Fiction House'),
('6','Harry Potter is about to start his fifth year at Hogwarts School of Witchcraft and Wizardry. Unlike most schoolboys, Harry never enjoys his summer holidays, but this summer is even worse than usual. The Dursleys, of course, are making his life a misery, but even his best friends, Ron and Hermione, seem to be neglecting him.','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1546910265i/2.jpg','2003-06-21','Harry Potter and the Order of the Phoenix',912,'jkrowling','Future Fiction House'),
('7','It is the middle of the summer, but there is an unseasonal mist pressing against the windowpanes. Harry Potter is waiting nervously in his bedroom at the Dursleys house in Privet Drive for a visit from Professor Dumbledore himself.','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1587697303i/1.jpg','2005-07-16','Harry Potter and the Half-Blood Prince',652,'jkrowling','Future Fiction House'),
('8','Harry has been burdened with a dark, dangerous and seemingly impossible task: that of locating and destroying Voldemorts remaining Horcruxes. Never has Harry felt so alone, or faced a future so full of shadows.','https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1663805647i/136251.jpg','2007-07-21','Harry Potter and the Deathly Hallows',759,'jkrowling','Future Fiction House');";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO book_genres (book_isbn, genres_genre_type) VALUES
('1','scifi'),
('1','fantasy'),
('2','adventure'),
('2','fantasy'),
('3','adventure'),
('3','fantasy'),
('4','adventure'),
('4','fantasy'),
('5','adventure'),
('5','fantasy'),
('6','adventure'),
('6','fantasy'),
('7','adventure'),
('7','fantasy'),
('8','adventure'),
('8','fantasy');";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO review (book_isbn, customer_username, description, post_date) VALUES
('1','techguru','Excelente leitura, muito envolvente e bem escrito.', '2024-05-01'),
('1','historybuff','História interessante, mas o ritmo é um pouco lento.', '2024-05-02');";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO rating (book_isbn, customer_username, rating) VALUES
('1','techguru',5),
('1','historybuff',3);";

docker exec -it postgres psql -U postgres -d cool -c "
INSERT INTO bookshelf (id, name, privacy, customer_username) VALUES
(1, 'currently_reading', 'PUBLIC', 'techguru'),
(2, 'already_read', 'PUBLIC', 'techguru'),
(3, 'did_not_finish', 'PUBLIC', 'techguru'),
(4, 'want_to_read', 'PUBLIC', 'techguru'),
(5, 'bookshelf1', 'PUBLIC', 'techguru'),
(6, 'currently_reading', 'PUBLIC', 'jkrowling'),
(7, 'already_read', 'PUBLIC', 'jkrowling'),
(8, 'did_not_finish', 'PUBLIC', 'jkrowling'),
(9, 'want_to_read', 'PUBLIC', 'jkrowling'),
(10, 'bookshelf1', 'PUBLIC', 'jkrowling');";

docker exec -it postgres psql -U postgres -d cool -c "
INSERT INTO personal_book (insert_date, pages_read, book_isbn, bookshelf_id)
values
('2000-01-01', 100, '1', 1),
('2000-02-01', 100, '1', 1),
('2000-03-01', 100, '1', 1),
('2000-04-01', 100, '1', 1),
('2001-05-01', 100, '1', 2),
('2001-01-01', 100, '1', 3),
('2007-01-01', 100, '1', 5),
('2010-01-01', 100, '1', 6),
('2013-01-01', 100, '1', 7),
('2017-01-01', 100, '1', 9),
('2017-01-01', 100, '1', 10);";


