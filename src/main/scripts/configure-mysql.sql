## Use this to run MySQL in a local Docker container
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql
# docker run --name dave-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql

# Connect to MySQL and run as root user
# Create databases
CREATE DATABASE sfg_dev;
CREATE DATABASE sfg_prod;

# Create database service accounts
CREATE USER 'sfg_dev_user'@'localhost' IDENTIFIED BY 'edavbre';
CREATE USER 'sfg_prod_user'@'localhost' IDENTIFIED BY 'edavbre';

CREATE USER 'sfg_dev_user'@'%' IDENTIFIED BY 'edavbre';
CREATE USER 'sfg_prod_user'@'%' IDENTIFIED BY 'edavbre';

# Database Grants
GRANT SELECT ON sfg_dev.* TO 'sfg_dev_user'@'localhost';
GRANT INSERT ON sfg_dev.* TO 'sfg_dev_user'@'localhost';
GRANT UPDATE ON sfg_dev.* TO 'sfg_dev_user'@'localhost';
GRANT DELETE ON sfg_dev.* TO 'sfg_dev_user'@'localhost';
GRANT SELECT ON sfg_prod.* TO 'sfg_prod_user'@'localhost';
GRANT INSERT ON sfg_prod.* TO 'sfg_prod_user'@'localhost';
GRANT UPDATE ON sfg_prod.* TO 'sfg_prod_user'@'localhost';
GRANT DELETE ON sfg_prod.* TO 'sfg_prod_user'@'localhost';

GRANT SELECT ON sfg_dev.* TO 'sfg_dev_user'@'%';
GRANT INSERT ON sfg_dev.* TO 'sfg_dev_user'@'%';
GRANT UPDATE ON sfg_dev.* TO 'sfg_dev_user'@'%';
GRANT DELETE ON sfg_dev.* TO 'sfg_dev_user'@'%';
GRANT SELECT ON sfg_prod.* TO 'sfg_prod_user'@'%';
GRANT INSERT ON sfg_prod.* TO 'sfg_prod_user'@'%';
GRANT UPDATE ON sfg_prod.* TO 'sfg_prod_user'@'%';
GRANT DELETE ON sfg_prod.* TO 'sfg_prod_user'@'%';
