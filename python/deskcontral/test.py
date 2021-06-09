
import random
import time
import pyperclip
import pyautogui

f = open('target.txt', mode='r', encoding='utf-8')
content = f.readlines()

time.sleep(5)

pyautogui.click(600, 400)

for j in content:
    for i in j:
        print(i)
        pyperclip.copy(i)
        pyautogui.hotkey('ctrl', 'v')
        time.sleep(random.random())
