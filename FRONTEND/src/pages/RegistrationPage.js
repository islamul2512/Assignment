// src/pages/RegistrationPage.js

import React from 'react';
import RegistrationList from '../components/RegistrationList';

const RegistrationPage = () => {
  return (
    <div className="container mt-4">
      <h2>Registrations</h2>
      <RegistrationList />
    </div>
  );
};

export default RegistrationPage;
