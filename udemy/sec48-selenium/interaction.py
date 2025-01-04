from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.common.keys import Keys

chrome_options = webdriver.ChromeOptions()
chrome_options.add_experimental_option('detach', True)

driver = webdriver.Chrome(options=chrome_options)
driver.get('https://secure-retreat-92358.herokuapp.com/')

fName = driver.find_element(By.NAME, 'fName')
lName = driver.find_element(By.NAME, 'lName')
email = driver.find_element(By.NAME, 'email')

fName.send_keys('Peter')
lName.send_keys('Kol')
email.send_keys('peter.kol@python.uk')
email.send_keys(Keys.ENTER)