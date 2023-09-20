import React, { useEffect, useState } from 'react';
import { plot_colors, red_color } from '../components/utils/Styles';
import axios from 'axios';

const Plot = () => {
    const [points, setPoints] = useState([]);
    const [groupLines, setGroupLines] = useState([]);
    const [iterationNumber, setIterationNumber] = useState(null);

    const [frameProperties, setFrameProperties] = useState({
        width: parseInt(window.innerWidth * 0.55),
        height: parseInt(window.innerHeight - 100),
    });
  
    useEffect(() => { 
        axios.post(`http://localhost:8080/ga/frameProperties`, null, {
            params: {
                width: frameProperties.width - 50,
                height: frameProperties.height - 100,
                },
            });
      }, []);

    useEffect(() => {
        axios.get(`http://localhost:8080/ga/paths`)
            .then((res) => {
                setGroupLines(res.data);
            })
    }, [groupLines]);

    useEffect(() => {
        axios.get(`http://localhost:8080/ga/iterationNumber`)
            .then((res) => {
                setIterationNumber(res.data);
            })
    }, [iterationNumber, points]);

    useEffect(() => {
        axios.get(`http://localhost:8080/ga/cities`)
            .then((res) => {
                const receivedPoints = res.data;
                setPoints(receivedPoints);
            });
    }, [points]);

    return (
        <div>
            <svg width={frameProperties.width} height={frameProperties.height}>
                {groupLines.length != 0 ? (
                    groupLines.map((list, listIndex) => (
                        <g key={listIndex}>
                        {list.map((line, lineIndex) => (
                            <line
                                key={lineIndex}
                                x1={line.x1}
                                y1={line.y1}
                                x2={line.x2}
                                y2={line.y2}
                                stroke={listIndex === iterationNumber - 1 ? red_color : plot_colors[listIndex % plot_colors.length]}
                                strokeWidth={listIndex === iterationNumber - 1 ? '5' : '1'} 
                            />
                        ))}
                        </g>
                    ))
                ) : (
                    null
                 )}
                {points.map((point, index) => (
                    <g key={index}>
                        <circle
                            cx={point.x}
                            cy={point.y}
                            r="10"
                            fill= {red_color}
                            strokeWidth="3"
                        />
                        <text
                            x={point.x}
                            y={point.y}
                            textAnchor="middle"
                            alignmentBaseline="middle"
                            fill="white"
                            fontFamily="Montserrat"
                            fontSize="11px"
                            fontWeight="700"
                        >
                        {point.number}
                        </text>
                    </g>
                ))}
            </svg>
        </div>
    );
}

export default Plot;