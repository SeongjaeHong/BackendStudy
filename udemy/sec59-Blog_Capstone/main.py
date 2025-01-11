from flask import Flask, render_template
import requests

app = Flask(__name__)

@app.route('/')
@app.route('/index.html')
def main():
    response = requests.get('https://api.npoint.io/495f53c32892edd20f6e')
    posts = response.json()
    return render_template('index.html', posts=posts)

@app.route('/post.html')
def page_post():
    return render_template('post.html')

@app.route('/about.html')
def page_about():
    return render_template('about.html')

@app.route('/contact.html')
def page_contact():
    return render_template('contact.html')

if __name__ == '__main__':
    app.run(debug=True)