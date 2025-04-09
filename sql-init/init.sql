-- Create user-service if it doesn't exist
CREATE DATABASE IF NOT EXISTS `user-service`;

-- Create airport-service if it doesn't exist
CREATE DATABASE IF NOT EXISTS `airport-service`;

-- Grant privileges
GRANT ALL PRIVILEGES ON `user-service`.* TO 'user'@'%';
GRANT ALL PRIVILEGES ON `airport-service`.* TO 'user'@'%';

FLUSH PRIVILEGES;
