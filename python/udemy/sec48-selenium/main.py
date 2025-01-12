from selenium import webdriver
from selenium.webdriver.common.by import By
from datetime import datetime

# Keep Chrome browser open after program finishes.
# chrome_options = webdriver.ChromeOptions()
# chrome_options.add_experimental_option("detach", True)
# driver = webdriver.Chrome(options=chrome_options)

driver = webdriver.Chrome()
driver.get('https://www.python.org/')
event_table = driver.find_elements(By.CSS_SELECTOR, '.event-widget .menu li')
events = dict()
for idx, event in enumerate(event_table):
    time = event.find_element(By.CSS_SELECTOR, 'time').get_attribute('datetime')
    time = datetime.fromisoformat(time).strftime('%Y-%m-%d')
    name = event.find_element(By.CSS_SELECTOR, 'a').text
    events[idx] = {
        'time': time,
        'name': name
    }

print(events)
