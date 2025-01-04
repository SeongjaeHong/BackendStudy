from selenium import webdriver
from selenium.webdriver.common.by import By
from time import time, sleep

options = webdriver.ChromeOptions()
options.add_experimental_option('detach', True)
driver = webdriver.Chrome(options=options)
driver.get('https://orteil.dashnet.org/cookieclicker/')
cookie = driver.find_element(By.ID, 'cookieAnchor')

while True:
    try:
        driver.find_element(By.ID, 'langSelect-EN').click()
        break
    except:
        sleep(0.5)
        continue
products = driver.find_element(By.ID, 'products')
cookie = driver.find_element(By.ID, 'bigCookie')
start = time()
last_purchase = time()
while time() - start < 60:
    try:
        cookie.click()

        # Check products
        if time() - last_purchase > 5:
            try:
                available_products = products.find_elements(By.CLASS_NAME, 'enabled')
                if available_products:
                    available_products[-1].click()
                    last_purchase = time()
            except Exception as e:
                products = driver.find_element(By.ID, 'products')
                continue

    except Exception as e:
        cookie = driver.find_element(By.ID, 'bigCookie')
        sleep(1)
        continue

