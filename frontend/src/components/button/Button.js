import React, { useState } from 'react';
import './Button.css'

const Button = ({ onClick, text, color }) => {
    const [isHovered, setIsHovered] = useState(false);
  
    const buttonStyle = {
        backgroundColor: isHovered ? 'white' : color,
        color: isHovered ? 'rgba(66, 67, 67, 1)' : "white",
        transition: '0.3s all', 
    };
  
    return (
        <button
            style={buttonStyle}
            onClick={onClick}
            onMouseEnter={() => setIsHovered(true)}
            onMouseLeave={() => setIsHovered(false)}
        >
            {text}
        </button>
    );
};
  
export default Button;

  
  
  
  
  