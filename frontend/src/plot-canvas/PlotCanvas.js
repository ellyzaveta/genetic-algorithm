import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Plot from './Plot';
import "./PlotCanvas.css"

const PlotCanvas = () => {
    
    return (
        <div className='plot-canvas-container'>
            <div className='plot-canvas-content'>
                <Plot></Plot>
            </div>
        </div>
    );
}

export default PlotCanvas;