from flask import Flask
import random

app = Flask(__name__)

def make_bold(func):
    def _make_bold():
        return f'<b>{func()}</b>'
    return _make_bold

def align_center(func):
    def _align_center():
        return f'<p style="text-align:center">{func()}</p>'
    return _align_center

answer = random.randint(0, 9)
@app.route('/')
def greet():
    return ('<b><h1>Guess a number between 0 and 9</h1></b>'
            '<img src="https://media.giphy.com/media/3o7aCSPqXE5C6T8tBC/giphy.gif" width="480" height="480">')

@app.route('/<int:number>')
def guess(number):
    if number > answer:
        return ('<b><h1>Too high, try again!</h1></b>'
            '<img src="https://media.giphy.com/media/3o6ZtaO9BZHcOjmErm/giphy.gif" width="480" height="480">')
    elif number < answer:
        return ('<b><h1>Too low, try again!</h1></b>'
                '<img src="https://media.giphy.com/media/jD4DwBtqPXRXa/giphy.gif" width="480" height="480">')
    else:
        return ('<b><h1 style="color: green">You found me!</h1></b>'
                '<img src="https://media.giphy.com/media/4T7e4DmcrP9du/giphy.gif" width="480" height="480">')

if __name__ == '__main__':
    app.run(debug=True, port=5004)