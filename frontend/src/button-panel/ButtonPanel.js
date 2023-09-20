import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Button from '../components/button/Button';
import { Slider } from '@mui/material';
import {sliderStyle, sliderStyleRed } from "../components/utils/Styles.js"
import MapPropertiesTable from '../components/map-properties-table/MapPropertiesTable';
import Table from "../components/table/Table"

import './ButtonPanel.css'

const ButtonPanel = () => {

    const [citiesNumber, setCitiesNumber] = useState(20);
    const [iterationNumber, setIterationNumber] = useState(20);
    const [mutationPercent, setMutationPercent] = useState(5.0);

    const [windowDimensions, setWindowDimensions] = useState({
        width: window.innerWidth,
        height: window.innerHeight,
    });

    const handleCitiesNumberSliderChange = (event, newValue) => {
        setCitiesNumber(newValue);
    };

    const handleIterationNumberSliderChange = (event, newValue) => {
        setIterationNumber(newValue);
    };

    const handleMutationPercentSliderChange = (event, newValue) => {
        setMutationPercent(newValue);
    };

    const handleSpeedSliderChange = (event, newValue) => {
        updateDelay(newValue);
    };

    const updateDelay = async (delay) => {
        try {
            await axios.post(`http://localhost:8080/ga/delay?delay=${delay}`);
        } catch (error) {
        }
    }

    const handleStartButtonClick = (event) => {
        axios.get(`http://localhost:8080/ga/start`);
    }

    const handleGenerationButtonClick = (e) => {
        e.preventDefault();

        try {
            axios.post(`http://localhost:8080/ga/userInput`, null, {
                params: {
                    iterationNumber: iterationNumber,
                    citiesNumber: citiesNumber,
                    mutationPercent: mutationPercent
                },
            });
        } catch (error) {
            console.error('Error sending data:', error);
        }
    };

    useEffect(() => {
        axios.post(`http://localhost:8080/ga/frameProperties`, null, {
                params: {
                    width: parseInt(window.innerWidth * 0.5 - 50),
                    height: parseInt(window.innerHeight - 100),
                },
            });
    }, []);

    return (
        <div className='button-panel-container'>
            <div className='content'>
                <div className='header'>
                    <h2 className='title'>CLASSIC GENETIC ALGORITHM</h2>
                    <h3 className='subtitle'>TRAVELING SALESMAN PROBLEM</h3>
                </div>
                <div className='slider-container'>
                    <div className='slider'>
                        <p className='slider-heading'>Number of cities</p>
                        <Slider
                            aria-label="Always visible"
                            sx={sliderStyle}
                            step={1}
                            min={10}
                            max={100}
                            valueLabelDisplay="on"
                            defaultValue={20}
                            onChange={handleCitiesNumberSliderChange}
                        />    
                    </div>
                    <div className='slider'>
                        <p className='slider-heading'>Number of iterations</p>
                        <Slider
                            aria-label="Always visible"
                            sx={sliderStyle}
                            step={1}
                            min={1}
                            max={120}
                            valueLabelDisplay="on"
                            defaultValue={20}
                            onChange={handleIterationNumberSliderChange}
                        />    
                    </div>
                    <div className='slider'>
                        <p className='slider-heading'>Mutation percent</p>
                        <Slider
                            aria-label="Always visible"
                            sx={sliderStyle}
                            step={0.5}
                            min={1}
                            max={100}
                            valueLabelDisplay="on"
                            defaultValue={5.0}
                            onChange={handleMutationPercentSliderChange}
                        />    
                    </div>
                </div>
                <div className='panel-button'>
                    <Button text="Generate map" color="rgba(92, 166, 232, 1)" onClick={handleGenerationButtonClick}></Button>
                </div>
                <div className='properties-table'>
                    <MapPropertiesTable></MapPropertiesTable>
                </div>
                <div className='panel-button'>
                    <Button text="Start" color="rgba(255, 151, 151, 1)" onClick={handleStartButtonClick}></Button>
                </div>
                <div className='result-table'>
                    <Table></Table>
                </div>
                <div className='slider'>
                        <p className='slider-heading'>Animation speed</p>
                        <Slider
                            aria-label="Always visible"
                            sx={sliderStyleRed}
                            step={1}
                            min={1}
                            max={5}
                            valueLabelDisplay="on"
                            defaultValue={2}
                            onChange={handleSpeedSliderChange}
                        />    
                </div>
            </div>
            
        </div>
    );

}

export default ButtonPanel;