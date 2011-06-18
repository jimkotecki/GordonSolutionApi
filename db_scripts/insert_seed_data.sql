-- Start Insert of seed data

-- Users

INSERT INTO "user"(
       id,user_name, user_password, user_email)
VALUES (1, 'SYSTEM', 'SYSTEM', 'SYSTEM');  

INSERT INTO "user"(
       id,user_name, user_password, user_email)
VALUES (2, 'test', 'test', 'test@test.com');

-- Links

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (1, 'SEED', 'http://www.cbssports.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (2, 'SEED', 'http://www.cnn.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id,crawl)
VALUES (3, 'SEED', 'http://www.reddit.com', 1,true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id,crawl)
VALUES (4, 'SEED', 'http://www.google.com', 1,true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (5, 'SEED', 'http://www.foxnews.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (6, 'SEED', 'http://www.huffingtonpost.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (7, 'SEED', 'http://www.computerworld.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (8, 'SEED', 'http://www.cnet.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (9, 'SEED', 'http://www.chicagotribune.com', 1, true);

INSERT INTO link_info(
            id,link_type, long_url, 
            user_id, crawl)
VALUES (10, 'SEED', 'http://messages.yahoo.com/yahoo/Recreation_%26_Sports/Sports/Football_%28American%29/index.html', 1, true);


 