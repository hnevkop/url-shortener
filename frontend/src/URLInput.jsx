import React from 'react';

class URLInput extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: '', shortUrl: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();

        const response = await fetch('/api/urls', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ originalUrl: this.state.value })
        });
        const content = await response.text();

        this.setState({shortUrl: content});
    }

    render() {
        return <div>
            <form onSubmit={this.handleSubmit}>
                <label>
                    Original URL:
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Shorten!" />
            </form>
            <div>Short URL: {this.state.shortUrl}</div>
        </div>;
    }
}

export default URLInput;