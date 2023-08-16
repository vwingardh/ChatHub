import axios from 'axios';
import React, { useState, useEffect } from 'react';

export const DisplayActiveUsers: React.FC<{ activeUsers: number }> = ({activeUsers}) => {

    return <div>Active Users: {activeUsers}</div>;
};