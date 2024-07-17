import React from 'react';
import './App.css';
import URLInput from './URLInput';
import URLRedirect from './URLRedirect';

function App() {
    return (
        <div className="App">
            <header className="App-header">
                <URLInput />
                <URLRedirect />
            </header>
        </div>
    );
}

export default App;