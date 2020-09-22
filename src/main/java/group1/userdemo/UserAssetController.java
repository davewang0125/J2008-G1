package group1.userdemo;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v2")
public class UserAssetController {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private UserAssetRepository userAssetRepository;

    //GET method to fetch all userAssets
    @GetMapping("/user_assets")
    public List<UserAsset> getAllUserAssets() {
        return userAssetRepository.findAll();
    }

    //Get method to fetch userAsset by userId
    @GetMapping("/user_assets/{id}")
    public ResponseEntity<UserAsset> getUserAssetById(@PathVariable(value = "id") Long assetId) throws Exception {
        UserAsset userAsset = userAssetRepository.findById(assetId)
                .orElseThrow(() -> new Exception("UserAsset" + assetId + "not found"));

        return ResponseEntity.ok().body(userAsset);
    }

    // POST method to create a userAsset
    @PostMapping("/user_assets")
    public UserAsset createUserAsset(@Valid @RequestBody UserAsset userAsset) {
        logger.log(Logger.Level.INFO, "--------- " + userAsset.getContent());
        logger.log(Logger.Level.INFO, "--------- " + userAsset.getAssetType());

        logger.log(Logger.Level.INFO, "--------- " + userAsset.getUserId());

        return userAssetRepository.save(userAsset);
    }


    // PUT method to update a userAsset's details
    @PutMapping("/user_assets/{id}")
    public ResponseEntity<UserAsset> updateUserAsset(@PathVariable(value="id") Long assetId,
                                                     @Valid @RequestBody UserAsset userAssetDetails) throws Exception {
        UserAsset userAsset = userAssetRepository.findById(assetId)
                .orElseThrow(() -> new Exception("UserAsset " + assetId + "not found"));
        userAsset.setUserId(userAssetDetails.getUserId());
        userAsset.setContent(userAssetDetails.getContent());
        userAsset.setAssetType(userAssetDetails.getAssetType());

        final UserAsset updatedUserAsset = userAssetRepository.save(userAsset);
        return ResponseEntity.ok(updatedUserAsset);
    }

    // DELETE method to delete a userAsset
    @DeleteMapping("/user/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value="id") Long assetId) throws Exception {
        UserAsset userAsset = userAssetRepository.findById(assetId)
                .orElseThrow(() -> new Exception("UserAsset " + assetId + " not found"));

        userAssetRepository.delete(userAsset);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}
