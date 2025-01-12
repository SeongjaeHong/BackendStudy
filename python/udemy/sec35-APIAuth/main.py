import requests

def diff_percent(price_old, price_cur):
    return round((price_cur - price_old) / price_old, 2)

def main():
    API_KEY = 'EYWQHL62GXOV4M8E'
    STOCK = "TSLA"
    STOCK_ENDPOINT = "https://www.alphavantage.co/query?"

    stock_url = (f'{STOCK_ENDPOINT}'
                 f'function=TIME_SERIES_DAILY'
                 f'&symbol={STOCK}'
                 f'&apikey={API_KEY}')
    res_stock = requests.get(stock_url)
    data_stock = res_stock.json()

    try:
        times = list(data_stock['Time Series (Daily)'].keys())
        price_closing_1d = float(data_stock['Time Series (Daily)'][times[0]]['4. close'])
        price_closing_2d = float(data_stock['Time Series (Daily)'][times[1]]['4. close'])

        print('Raise by {}%: {} -> {}'.format(diff_percent(price_closing_2d, price_closing_1d) * 100,
                                             price_closing_2d,
                                             price_closing_1d))
    except KeyError:
        print('Out of valid API call count\n{}'.format(data_stock['Information']))

if __name__ == '__main__':
    main()