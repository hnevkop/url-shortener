import React from 'react';

class URLRedirect extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: '', originalUrl: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    async handleSubmit(event) {
        event.preventDefault();
        const response = await fetch('/api/urls/'+this.state.value);
        const content = await response.text();

        this.setState({ originalUrl: content });
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>
                        Short URL code:
                        <input type="text" value={this.state.value} onChange={this.handleChange} />
                    </label>
                    <input type="submit" value="Resolve!" />
                </form>
                {this.state.originalUrl && <div>Original URL: {this.state.originalUrl}</div>}
            </div>
        );
    }
}

export default URLRedirect;