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

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO customer (dtype, username, birth_date, country, description, email, gender, interests, name, password, profile_banner_url, profile_image_url, pronouns) VALUES
('customer', 'techguru', '1985-04-12', 'USA', 'Tech enthusiast and blogger', 'techguru@example.com', 'Male', 'Technology, Gadgets, Coding', 'John Doe', 'securepassword123', 'https://example.com/banners/techguru.jpg', 'https://randomuser.me/api/portraits/men/1.jpg', 'he/him'),
('customer', 'wellnessqueen', '1990-07-19', 'Canada', 'Wellness coach and nutritionist', 'wellnessqueen@example.com', 'Female', 'Health, Nutrition, Yoga', 'Emily Clark', 'yogapassword456', 'https://example.com/banners/wellnessqueen.jpg', 'https://randomuser.me/api/portraits/women/2.jpg', 'she/her'),
('customer', 'historybuff', '1978-03-25', 'UK', 'History professor and author', 'historybuff@example.com', 'Male', 'History, Archaeology, Museums', 'Michael Smith', 'historypass789', 'https://example.com/banners/historybuff.jpg', 'https://randomuser.me/api/portraits/men/3.jpg', 'he/him'),
('customer', 'fictionfan', '1992-11-08', 'Australia', 'Avid reader and fiction writer', 'fictionfan@example.com', 'Non-binary', 'Literature, Writing, Reading', 'Alex Johnson', 'fictionpassword321', 'https://example.com/banners/fictionfan.jpg', 'https://randomuser.me/api/portraits/women/4.jpg', 'they/them'),
('author', 'randallmunroe', '1988-06-15', 'Germany', 'Environmental activist and blogger', 'greenearth@example.com', 'Female', 'Environment, Sustainability, Nature', 'Sophia Müller', 'greenpassword654', 'https://example.com/banners/greenearth.jpg', 'https://randomuser.me/api/portraits/men/5.jpg', 'she/her'),
('customer', 'edtechgeek', '1994-02-20', 'India', 'Educational technology specialist', 'edtechgeek@example.com', 'Male', 'Education, Technology, Innovation', 'Raj Patel', 'edtechpassword987', 'https://example.com/banners/edtechgeek.jpg', 'https://randomuser.me/api/portraits/women/6.jpg', 'he/him'),
('customer', 'scienceseeker', '1980-10-05', 'France', 'Science journalist and researcher', 'scienceseeker@example.com', 'Female', 'Science, Research, Space', 'Isabelle Laurent', 'sciencepassword123', 'https://example.com/banners/scienceseeker.jpg', 'https://randomuser.me/api/portraits/men/7.jpg', 'she/her'),
('author', 'jkrowling', '1987-09-12', 'Italy', 'Artist and art historian', 'artisanartist@example.com', 'Male', 'Art, History, Painting', 'Luca Rossi', 'artpassword456', 'https://example.com/banners/artisanartist.jpg', 'https://randomuser.me/api/portraits/women/8.jpg', 'he/him'),
('customer', 'culinarymaster', '1991-01-29', 'Spain', 'Professional chef and food critic', 'culinarymaster@example.com', 'Non-binary', 'Cooking, Food, Travel', 'Maria Garcia', 'culinarypassword789', 'https://example.com/banners/culinarymaster.jpg', 'https://randomuser.me/api/portraits/men/9.jpg', 'they/them'),
('customer', 'mindfulmentor', '1983-05-17', 'Netherlands', 'Mindfulness coach and author', 'mindfulmentor@example.com', 'Female', 'Mindfulness, Meditation, Self-help', 'Anna Jansen', 'mindfulpassword321', 'https://example.com/banners/mindfulmentor.jpg', 'https://randomuser.me/api/portraits/women/10.jpg','she/her');";

docker exec -it postgres psql -U postgres -d cool -c "ALTER TABLE book ALTER COLUMN description TYPE TEXT;";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO book (isbn, description, launch_date, title, total_page_numbers, author_username, publisher_name) 
VALUES (
    '1',
    'Bestselling author and cartoonist Randall Munroe explains how to predict the weather by analyzing the pixels of your Facebook photos. He teaches you how to tell if you are a baby boomer or a 90s kid by measuring the radioactivity of your teeth. He offers tips for taking a selfie with a telescope, crossing a river by boiling it, and powering your house by destroying the fabric of space-time. And if you want to get rid of the book once you are done with it, he walks you through your options for proper disposal, including dissolving it in the ocean, converting it to a vapor, using tectonic plates to subduct it into the Earths mantle, or launching it into the Sun. By exploring the most complicated ways to do simple tasks, Munroe doesnt just make things difficult for himself and his readers. As he did so brilliantly in What If?, Munroe invites us to explore the most absurd reaches of the possible. Full of clever infographics and amusing illustrations, How To is a delightfully mind-bending way to better understand the science and technology underlying the things we do every day.',
    '2019-09-03',
    'How To: Absurd Scientific Advice for Common Real-World Problems',
    308,
    'randallmunroe',
    'Science Today Press'
);";

docker exec -it postgres psql -U postgres -d cool -c "INSERT INTO book_genres (book_isbn, genres_genre_type) VALUES ('1','SciFi'),('1','Fantasy');";
