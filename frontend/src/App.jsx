import React from 'react';

import './App.css';
import URLInput from './URLInput';
import URLRedirect from './URLRedirect';

function App() {
  const [url, setUrl] = React.useState(null);

  return (
      <div className="App">
        <header className="App-header">
          <URLInput onUrlChange={setUrl} />
          {url && <URLRedirect url={url} />}
        </header>
      </div>
  );
}

export default App;