import React from 'react';
import ButtonPanel from './button-panel/ButtonPanel';
import PlotCanvas from './plot-canvas/PlotCanvas';

import './App.css';

function App() {
    return (
        <div className='app'>
            <PlotCanvas></PlotCanvas>
            <ButtonPanel></ButtonPanel>           
        </div>
    );
}

export default App;
