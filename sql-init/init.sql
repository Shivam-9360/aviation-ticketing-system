-- Create databases
CREATE DATABASE IF NOT EXISTS `user-service`;
CREATE DATABASE IF NOT EXISTS `airport-service`;

-- Grant permissions
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root';
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;