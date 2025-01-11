from flask import Flask, render_template

app = Flask(__name__)

@app.route('/')
@app.route('/index.html')
def main():
    return render_template('index.html')

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