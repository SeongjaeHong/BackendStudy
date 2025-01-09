from flask import Flask, render_template
from post import Post

app = Flask(__name__)

@app.route('/')
def home():
    return '<b><h1>There is nothing here!</h1></b>'

@app.route('/post')
def main_page():
    return Post().get_posts()


@app.route('/post/<int:post_id>')
def get_post(post_id):
    return Post().get_post(post_id)


if __name__ == "__main__":
    app.run(debug=True)
