from flask import Flask, render_template, request
import requests

app = Flask(__name__)
response = requests.get('https://api.npoint.io/495f53c32892edd20f6e')
posts = response.json()

@app.route('/')
@app.route('/index.html')
def main():
    return render_template('index.html', posts=posts)

@app.route('/post.html', methods=['GET'])
def page_post():
    post_id = int(request.args['post_id']) - 1
    return render_template('post.html',
                           post=posts[post_id])

@app.route('/about.html')
def page_about():
    return render_template('about.html')

@app.route('/contact.html')
def page_contact():
    return render_template('contact.html')

if __name__ == '__main__':
    app.run(debug=True)